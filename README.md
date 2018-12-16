# springboot-web3j-ethereum

The goal of this project is to implement an `Ethereum Smart Contract` called `SoccerManager` and deploy it to
[`Ethereum Blockchain`](https://www.ethereum.org) running locally using [`ethereum/client-go`](https://github.com/ethereum/go-ethereum)
docker image. Besides, we will develop two Spring Boot applications (`ethereuma-api` and `player-api`) that use
[`Web3j`](https://docs.web3j.io/getting_started.html) library to communicate with `Ethereum Blockchain` (create wallet,
transfer ether, etc) and the `SoccerManager` smart contract.

## About `SoccerManager` smart contract

`SoccerManager` is a smart contract that handles soccer players. Once deployed, this smart contract has a list of
soccer players already pre-defined. Initially, the agent of those pre-defined players is the owner of the contract (the wallet
address who deployed the contract). Besides, only the owner of the contract can add players. Other wallets (agent wallets)
can buy soccer players. Once an agent wallet buy a soccer player, it becomes the owner of the player.

`SoccerManager` was written using the online IDE [Remix](https://remix.ethereum.org)

## Start environment

- Open a terminal and go to `springboot-web3j-ethereum` root folder

- Run the docker command below. It starts a docker container in development mode and exposes `Ethereum RPC API` on port `8545`.
```
docker run -d --name ethereum -p 8545:8545 -p 30303:30303 ethereum/client-go:v1.8.20 --rpc --rpcaddr "0.0.0.0" --rpcapi="db,eth,net,web3,personal" --rpccorsdomain "*" --dev
```

> *NOTE*
> Run the command below if you want to enter in the Geth’s interactive JavaScript console inside Docker container. It provides
> a lot of features such as: create an wallet, check waller balance, transfer ether from one address to another, etc. I won't
> focus on it because I decided to implement such features in `ethereum-api` using `Web3j`.
> ```
> docker exec -it ethereum geth attach ipc:/tmp/geth.ipc
> ```

- Run the following script. It will compile Solidity `SoccerManager` code, `solidity/SoccerManager.sol`. When the
compilation finishes, it will produce the files: `solidity/SoccerManager.abi` and `solidity/SoccerManager.bin`. Next,
the script uses those two files to generate the `SoccerManager.java` on `ethereum-api/src/main/java/com/mycompany/ethereumapi/contract`
and `player-api/src/main/java/com/mycompany/playerapi/contract`. 
```
./compile-generate-soccermanager.sh
```

## Starting/Running the services

***Note. In order to run some commands/scripts, you must have [`jq`](https://stedolan.github.io/jq) installed on you machine***

### Start `ethereum-api`

- Open a new terminal and inside `springboot-web3j-ethereum/ethereum-api`, run the command below to start the service 
```
mvn spring-boot:run
```

- `ethereum-api` has a swagger site: http://localhost:8080/swagger-ui.html

### Create wallets, deploy contract and start `play-api` service

- Open a new terminal

- Create `owner contract` wallet
```
OWNER_CONTRACT_WALLET=$(curl -s -X POST "http://localhost:8080/api/wallets/create" -H "Content-Type: application/json" -d "{ \"password\": 123, \"initialBalance\": 10000000000000000000}" | jq '.')
OWNER_CONTRACT_WALLET_FILE=$(echo $OWNER_CONTRACT_WALLET | jq -r '.file')
OWNER_CONTRACT_WALLET_ADDR=$(echo $OWNER_CONTRACT_WALLET | jq -r '.address')
echo "### Owner wallet file => $OWNER_CONTRACT_WALLET_FILE"
```

- Deploy `SoccerManager` smart contract using the `owner contract` wallet
```
export ETHEREUM_CONTRACT_SOCCERMANAGER_ADDRESS=$(curl -s -X POST "http://localhost:8080/api/contracts/deploy/soccerManager" -H "Content-Type: application/json" -d "{ \"password\": 123, \"file\": \"$OWNER_CONTRACT_WALLET_FILE\", \"gasPrice\": 1, \"gasLimit\": 3000000}")
```

- Inside `springboot-web3j-ethereum/player-api`, run the following command to start `play-api` 
```
mvn spring-boot:run
```

### Test `player-api`

- Open a new terminal

- Create `new agent` wallet
```
NEW_AGENT_WALLET=$(curl -s -X POST "http://localhost:8080/api/wallets/create" -H "Content-Type: application/json" -d "{ \"password\": 123, \"initialBalance\": 10000000000000000000}" | jq '.')
NEW_AGENT_WALLET_FILE=$(echo $NEW_AGENT_WALLET | jq -r '.file')
NEW_AGENT_WALLET_ADDR=$(echo $NEW_AGENT_WALLET | jq -r '.address')
echo "### New agent wallet file => $NEW_AGENT_WALLET_FILE"
```

- Get player with id `1` using `new agent` wallet
```
curl -s -X POST "http://localhost:8081/api/players/get" -H "Content-Type: application/json" -d "{ \"password\": 123, \"file\": \"$NEW_AGENT_WALLET_FILE\", \"gasPrice\": 1, \"gasLimit\": 3000000, \"playerId\": 1}" | jq '.'
```

- Buy player with id `1`
```
curl -s -X POST "http://localhost:8081/api/players/buy" -H "Content-Type: application/json" -d "{ \"password\": 123, \"file\": \"$NEW_AGENT_WALLET_FILE\", \"gasPrice\": 1, \"gasLimit\": 3000000, \"playerId\": 1, \"weiValue\": 1000000000000000000}" | jq '.'
```

- Get player `new agent` has
```
curl -s -X POST "http://localhost:8081/api/agents/players" -H "Content-Type: application/json" -d "{ \"password\": 123, \"file\": \"$NEW_AGENT_WALLET_FILE\", \"gasPrice\": 1, \"gasLimit\": 3000000}" | jq '.'
```

- `player-api` has a swagger site: http://localhost:8081/swagger-ui.html

- **PS. Some `player-api` endpoints, such `POST /api/players/add`, requires that you use the `owner contract` wallet.**

## TODO

## References

- https://piotrminkowski.wordpress.com/2018/06/22/introduction-to-blockchain-with-java-using-ethereum-web3j-and-spring-boot/
- https://piotrminkowski.wordpress.com/2018/07/25/intro-to-blockchain-with-ethereum-web3j-and-spring-boot-smart-contracts/
