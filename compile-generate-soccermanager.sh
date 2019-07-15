#!/usr/bin/env bash

WEB3J_PATH=/Users/ivanfranchin/tools/web3j-4.3.0/bin # <= Set the correct web3j package in your machine

PROJECT_PATH=$PWD
SOLIDITY_PATH=${PROJECT_PATH}/solidity
ETHEREUM_API_PATH=${PROJECT_PATH}/ethereum-api
PLAYER_API_PATH=${PROJECT_PATH}/player-api

docker run --rm -v ${SOLIDITY_PATH}:/build ethereum/solc:stable /build/SoccerManager.sol --bin --abi --optimize --overwrite -o /build

${WEB3J_PATH}/web3j solidity generate -a=${SOLIDITY_PATH}/SoccerManager.abi -b=${SOLIDITY_PATH}/SoccerManager.bin -p com.mycompany.ethereumapi.contract -o ${ETHEREUM_API_PATH}/src/main/java
${WEB3J_PATH}/web3j solidity generate -a=${SOLIDITY_PATH}/SoccerManager.abi -b=${SOLIDITY_PATH}/SoccerManager.bin -p com.mycompany.playerapi.contract -o ${PLAYER_API_PATH}/src/main/java
