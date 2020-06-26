#!/usr/bin/env bash

if [ -z "$WEB3J_PATH" ]; then
  echo "WARNING: export to WEB3J_PATH environment variable the absolute path to Web3j!"
  exit 1
fi

WEB3J_PATH="${WEB3J_PATH}/bin"

PROJECT_PATH=$PWD
SOLIDITY_PATH=${PROJECT_PATH}/solidity
ETHEREUM_API_PATH=${PROJECT_PATH}/ethereum-api
PLAYER_API_PATH=${PROJECT_PATH}/player-api

docker run --rm -v ${SOLIDITY_PATH}:/build ethereum/solc:0.5.17 /build/SoccerManager.sol --bin --abi --optimize --overwrite -o /build

${WEB3J_PATH}/web3j solidity generate -a=${SOLIDITY_PATH}/SoccerManager.abi -b=${SOLIDITY_PATH}/SoccerManager.bin -p com.mycompany.ethereumapi.contract -o ${ETHEREUM_API_PATH}/src/main/java
${WEB3J_PATH}/web3j solidity generate -a=${SOLIDITY_PATH}/SoccerManager.abi -b=${SOLIDITY_PATH}/SoccerManager.bin -p com.mycompany.playerapi.contract -o ${PLAYER_API_PATH}/src/main/java
