pragma solidity ^0.5.0;

// 1 ether = 1000000000000000000 wei

contract SoccerManager {

    using MyUIntArraysLib for uint[];

    address private owner;
    uint private numPlayers;
    mapping (uint => Player) private players;
    mapping (address => uint[]) private agents;
    uint[] playerIds;

    struct Player {
        uint id;
        bool status;
        string name;
        uint price;
        string image;
        bool forSale;
        address payable agent;
    }

    event PlayerAdded (uint playerId, string name, uint price, bool forSale);
    event PlayerUpdated (uint playerId, bool oldForSaleValue, bool newForSaleValue);
    event PlayerBought (uint playerId, address oldAgent, address newAgent, uint price);

    modifier isOwner {
        require(owner == msg.sender, "You are not the contract owner");
        _;
    }

    constructor() public {
        owner = msg.sender;

        addPreDefinedPlayers();
    }

    function addPreDefinedPlayers() private {
        insertPlayer("WEVERTON", 1000000000000000000, "https://s3.amazonaws.com/sep-bucket-prod/wp-content/uploads/2019/05/02082731/weverton.png", msg.sender);
        insertPlayer("FELIPE MELO", 2000000000000000000, "https://s3.amazonaws.com/sep-bucket-prod/wp-content/uploads/2019/08/02080049/felipe-melo.png", msg.sender);
        insertPlayer("GUSTAVO GOMEZ", 3000000000000000000, "https://s3.amazonaws.com/sep-bucket-prod/wp-content/uploads/2020/01/02080153/gustavo-gomez.png", msg.sender);
    }

    function addPlayer(string memory name, uint price, string memory image) public isOwner returns (uint) {
        require(bytes(name).length > 0, "The argument 'name' cannot be empty");
        require(price > 0, "The argument 'price' must be > 0");

        uint id = insertPlayer(name, price, image, msg.sender);

        emit PlayerAdded(id, name, price, true);
        return id;
    }

    function insertPlayer(string memory name, uint price, string memory image, address payable agent) private returns (uint) {
        uint id = ++numPlayers;
        players[id] = Player(id, true, name, price, image, true, agent);
        playerIds.push(id);
        agents[agent].push(id);
        return id;
    }

    function updatePlayer(uint id, bool forSale) public returns (bool) {
        require(players[id].status, "Player with the informed id doesn't exist");
        require(players[id].forSale != forSale, "Player forSale status is the same already set");
        require(players[id].agent == msg.sender, "You are not the agent of the player");

        bool oldForSaleValue = players[id].forSale;
        players[id].forSale = forSale;

        emit PlayerUpdated(id, oldForSaleValue, forSale);
        return true;
    }

    function getPlayer(uint id) public view returns (uint, string memory, uint, string memory, bool, address) {
        require(players[id].status, "Player with the informed id doesn't exist");
        Player storage player = players[id];
        return (player.id, player.name, player.price, player.image, player.forSale, player.agent);
    }

    function getPlayersOfAgent() public view returns (uint[] memory) {
        return agents[msg.sender];
    }

    function getNumberOfPlayers() public view returns (uint) {
        return playerIds.length;
    }

    function buyPlayer(uint id) public payable returns (bool) {
        require(players[id].status, "Player doesn't exist");
        require(players[id].forSale, "Player is not for sale");
        require(msg.sender != players[id].agent, "You are already the player agent");
        require(msg.value == players[id].price, "Amount sent is incorrect");
        require(msg.sender.balance >= players[id].price, "You don't have enought ether on your balance");

        address payable fromAgent = players[id].agent;
        players[id].agent = msg.sender; // player has a new agent
        agents[players[id].agent].push(id);
        agents[fromAgent].removeValue(id);

        fromAgent.transfer(players[id].price);

        emit PlayerBought(id, fromAgent, msg.sender, players[id].price);
        return true;
    }

}

library MyUIntArraysLib {

    function removeValue(uint[] storage array, uint value) internal returns (bool) {
        for (uint i=0; i < array.length; i++) {
            if (array[i] == value) {
                array[i] = array[array.length-1];
                array.length--;
                return true;
            }
        }
        return false;
    }
}