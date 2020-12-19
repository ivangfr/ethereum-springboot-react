package com.mycompany.playerapi.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class SoccerManager extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b50600080546001600160a01b03191633179055620000376001600160e01b036200003d16565b62000342565b62000095604051806040016040528060088152602001672ba2ab22a92a27a760c11b815250670de0b6b3a76400006040518060800160405280605981526020016200131e60599139336001600160e01b036200015216565b50620000f16040518060400160405280600b81526020016a46454c495045204d454c4f60a81b815250671bc16d674ec800006040518060800160405280605c8152602001620013d5605c9139336001600160e01b036200015216565b506200014f6040518060400160405280600d81526020016c23aaa9aa20ab279023a7a6a2ad60991b8152506729a2241af62c00006040518060800160405280605e815260200162001377605e9139336001600160e01b036200015216565b50565b6001805481018082556040805160e08101825282815260208082018581528284018a8152606084018a90526080840189905260a084018790526001600160a01b03881660c085015260008681526002808552958120855181559251978301805460ff19169815159890981790975551805193949193620001db939285019291909101906200029d565b506060820151600382015560808201518051620002039160048401916020909101906200029d565b5060a0820151600591909101805460c09093015160ff1990931691151591909117610100600160a81b0319166101006001600160a01b03938416021790556004805460018181019092557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b018390559316600090815260036020908152604082208054958601815582529020909201829055509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002e057805160ff191683800117855562000310565b8280016001018555821562000310579182015b8281111562000310578251825591602001919060010190620002f3565b506200031e92915062000322565b5090565b6200033f91905b808211156200031e576000815560010162000329565b90565b610fcc80620003526000396000f3fe6080604052600436106100555760003560e01c80637de81fca1461005a578063aab48c521461008b578063e55ae4e8146101df578063eca2f3c81461030f578063fc94f54014610374578063fd6673f5146103a6575b600080fd5b6100776004803603602081101561007057600080fd5b50356103bb565b604080519115158252519081900360200190f35b34801561009757600080fd5b506101cd600480360360608110156100ae57600080fd5b8101906020810181356401000000008111156100c957600080fd5b8201836020820111156100db57600080fd5b803590602001918460018302840111640100000000831117156100fd57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295843595909490935060408101925060200135905064010000000081111561015857600080fd5b82018360208201111561016a57600080fd5b8035906020019184600183028401116401000000008311171561018c57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506106c8945050505050565b60408051918252519081900360200190f35b3480156101eb57600080fd5b506102096004803603602081101561020257600080fd5b503561088b565b6040805187815290810185905282151560808201526001600160a01b03821660a082015260c060208083018281528851928401929092528751606084019160e0850191908a019080838360005b8381101561026e578181015183820152602001610256565b50505050905090810190601f16801561029b5780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b838110156102ce5781810151838201526020016102b6565b50505050905090810190601f1680156102fb5780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b34801561031b57600080fd5b50610324610a54565b60408051602080825283518183015283519192839290830191858101910280838360005b83811015610360578181015183820152602001610348565b505050509050019250505060405180910390f35b34801561038057600080fd5b506100776004803603604081101561039757600080fd5b50803590602001351515610ab6565b3480156103b257600080fd5b506101cd610c34565b60008181526002602052604081206001015460ff16610418576040805162461bcd60e51b8152602060048201526014602482015273141b185e595c88191bd95cdb89dd08195e1a5cdd60621b604482015290519081900360640190fd5b60008281526002602052604090206005015460ff16610477576040805162461bcd60e51b8152602060048201526016602482015275506c61796572206973206e6f7420666f722073616c6560501b604482015290519081900360640190fd5b60008281526002602052604090206005015461010090046001600160a01b03163314156104eb576040805162461bcd60e51b815260206004820181905260248201527f596f752061726520616c72656164792074686520706c61796572206167656e74604482015290519081900360640190fd5b6000828152600260205260409020600301543414610550576040805162461bcd60e51b815260206004820152601860248201527f416d6f756e742073656e7420697320696e636f72726563740000000000000000604482015290519081900360640190fd5b600082815260026020526040902060030154333110156105a15760405162461bcd60e51b815260040180806020018281038252602c815260200180610ef3602c913960400191505060405180910390fd5b6000828152600260209081526040808320600501805433610100908102610100600160a81b031983161792839055918290046001600160a01b03908116865260038552838620805460018101825590875294862090940187905504919091168083529120610615908463ffffffff610c3a16565b506000838152600260205260408082206003015490516001600160a01b0384169282156108fc02929190818181858888f1935050505015801561065c573d6000803e3d6000fd5b506000838152600260209081526040918290206003015482518681526001600160a01b038516928101929092523382840152606082015290517f87e04f5f6a6261cdd2f775831d9ffe91bb9cacb100d78a01bba93e0be84c18069181900360800190a150600192915050565b600080546001600160a01b03163314610728576040805162461bcd60e51b815260206004820152601e60248201527f596f7520617265206e6f742074686520636f6e7472616374206f776e65720000604482015290519081900360640190fd5b60008451116107685760405162461bcd60e51b8152600401808060200182810382526023815260200180610ed06023913960400191505060405180910390fd5b600083116107bd576040805162461bcd60e51b815260206004820181905260248201527f54686520617267756d656e742027707269636527206d757374206265203e2030604482015290519081900360640190fd5b60006107cb85858533610cc7565b90507f0c67b919d648c2b4bd260225fe9b485cc70a7e619047dcf5bb598ae38a0b72888186866001604051808581526020018060200184815260200183151515158152602001828103825285818151815260200191508051906020019080838360005b8381101561084657818101518382015260200161082e565b50505050905090810190601f1680156108735780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1949350505050565b600081815260026020526040812060010154606090829082908290819060ff166108e65760405162461bcd60e51b8152600401808060200182810382526029815260200180610f6f6029913960400191505060405180910390fd5b60008781526002602081815260409283902080546003820154600583015483860180548851610100600183161581026000190190921698909804601f81018890048802890188019099528888529497939690959294600489019460ff841694919093046001600160a01b03169287918301828280156109a65780601f1061097b576101008083540402835291602001916109a6565b820191906000526020600020905b81548152906001019060200180831161098957829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959a5088945092508401905082828015610a345780601f10610a0957610100808354040283529160200191610a34565b820191906000526020600020905b815481529060010190602001808311610a1757829003601f168201915b505050505092508090509650965096509650965096505091939550919395565b33600090815260036020908152604091829020805483518184028101840190945280845260609392830182828015610aab57602002820191906000526020600020905b815481526020019060010190808311610a97575b505050505090505b90565b60008281526002602052604081206001015460ff16610b065760405162461bcd60e51b8152600401808060200182810382526029815260200180610f6f6029913960400191505060405180910390fd5b60008381526002602052604090206005015460ff1615158215151415610b5d5760405162461bcd60e51b815260040180806020018281038252602d815260200180610f42602d913960400191505060405180910390fd5b60008381526002602052604090206005015461010090046001600160a01b03163314610bba5760405162461bcd60e51b8152600401808060200182810382526023815260200180610f1f6023913960400191505060405180910390fd5b600083815260026020908152604091829020600501805485151560ff1982168117909255835187815260ff9091168015159382019390935280840191909152915190917f47116610d83e5898152286936a924c1198dab9b12f87e2eafc3bc39a14570f98919081900360600190a160019150505b92915050565b60045490565b6000805b8354811015610cbd5782848281548110610c5457fe5b90600052602060002001541415610cb557835484906000198101908110610c7757fe5b9060005260206000200154848281548110610c8e57fe5b6000918252602090912001558354610caa856000198301610e0e565b506001915050610c2e565b600101610c3e565b5060009392505050565b6001805481018082556040805160e08101825282815260208082018581528284018a8152606084018a90526080840189905260a084018790526001600160a01b03881660c085015260008681526002808552958120855181559251978301805460ff19169815159890981790975551805193949193610d4e93928501929190910190610e37565b506060820151600382015560808201518051610d74916004840191602090910190610e37565b5060a0820151600591909101805460c09093015160ff1990931691151591909117610100600160a81b0319166101006001600160a01b03938416021790556004805460018181019092557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b018390559316600090815260036020908152604082208054958601815582529020909201829055509392505050565b815481835581811115610e3257600083815260209020610e32918101908301610eb5565b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e7857805160ff1916838001178555610ea5565b82800160010185558215610ea5579182015b82811115610ea5578251825591602001919060010190610e8a565b50610eb1929150610eb5565b5090565b610ab391905b80821115610eb15760008155600101610ebb56fe54686520617267756d656e7420276e616d65272063616e6e6f7420626520656d707479596f7520646f6e2774206861766520656e6f75676874206574686572206f6e20796f75722062616c616e6365596f7520617265206e6f7420746865206167656e74206f662074686520706c61796572506c6179657220666f7253616c6520737461747573206973207468652073616d6520616c726561647920736574506c6179657220776974682074686520696e666f726d656420696420646f65736e2774206578697374a265627a7a72315820e2f95131317593473c441de172cdbd408c050bd5a70408ce2f447fd90352502f64736f6c6343000511003268747470733a2f2f73332e616d617a6f6e6177732e636f6d2f7365702d6275636b65742d70726f642f77702d636f6e74656e742f75706c6f6164732f323031392f30352f30323038323733312f7765766572746f6e2e706e6768747470733a2f2f73332e616d617a6f6e6177732e636f6d2f7365702d6275636b65742d70726f642f77702d636f6e74656e742f75706c6f6164732f323032302f30312f30323038303135332f6775737461766f2d676f6d657a2e706e6768747470733a2f2f73332e616d617a6f6e6177732e636f6d2f7365702d6275636b65742d70726f642f77702d636f6e74656e742f75706c6f6164732f323031392f30382f30323038303034392f66656c6970652d6d656c6f2e706e67";

    public static final String FUNC_ADDPLAYER = "addPlayer";

    public static final String FUNC_BUYPLAYER = "buyPlayer";

    public static final String FUNC_GETNUMBEROFPLAYERS = "getNumberOfPlayers";

    public static final String FUNC_GETPLAYER = "getPlayer";

    public static final String FUNC_GETPLAYERSOFAGENT = "getPlayersOfAgent";

    public static final String FUNC_UPDATEPLAYER = "updatePlayer";

    public static final Event PLAYERADDED_EVENT = new Event("PlayerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event PLAYERBOUGHT_EVENT = new Event("PlayerBought", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PLAYERUPDATED_EVENT = new Event("PlayerUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected SoccerManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SoccerManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SoccerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SoccerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<PlayerAddedEventResponse> getPlayerAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYERADDED_EVENT, transactionReceipt);
        ArrayList<PlayerAddedEventResponse> responses = new ArrayList<PlayerAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlayerAddedEventResponse typedResponse = new PlayerAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.forSale = (Boolean) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PlayerAddedEventResponse> playerAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PlayerAddedEventResponse>() {
            @Override
            public PlayerAddedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLAYERADDED_EVENT, log);
                PlayerAddedEventResponse typedResponse = new PlayerAddedEventResponse();
                typedResponse.log = log;
                typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.forSale = (Boolean) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PlayerAddedEventResponse> playerAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYERADDED_EVENT));
        return playerAddedEventFlowable(filter);
    }

    public List<PlayerBoughtEventResponse> getPlayerBoughtEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYERBOUGHT_EVENT, transactionReceipt);
        ArrayList<PlayerBoughtEventResponse> responses = new ArrayList<PlayerBoughtEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlayerBoughtEventResponse typedResponse = new PlayerBoughtEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.oldAgent = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.newAgent = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PlayerBoughtEventResponse> playerBoughtEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PlayerBoughtEventResponse>() {
            @Override
            public PlayerBoughtEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLAYERBOUGHT_EVENT, log);
                PlayerBoughtEventResponse typedResponse = new PlayerBoughtEventResponse();
                typedResponse.log = log;
                typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.oldAgent = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.newAgent = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PlayerBoughtEventResponse> playerBoughtEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYERBOUGHT_EVENT));
        return playerBoughtEventFlowable(filter);
    }

    public List<PlayerUpdatedEventResponse> getPlayerUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYERUPDATED_EVENT, transactionReceipt);
        ArrayList<PlayerUpdatedEventResponse> responses = new ArrayList<PlayerUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlayerUpdatedEventResponse typedResponse = new PlayerUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.oldForSaleValue = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.newForSaleValue = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PlayerUpdatedEventResponse> playerUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PlayerUpdatedEventResponse>() {
            @Override
            public PlayerUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLAYERUPDATED_EVENT, log);
                PlayerUpdatedEventResponse typedResponse = new PlayerUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.playerId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.oldForSaleValue = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.newForSaleValue = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PlayerUpdatedEventResponse> playerUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYERUPDATED_EVENT));
        return playerUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addPlayer(String name, BigInteger price, String image) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.Utf8String(image)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> buyPlayer(BigInteger id, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getNumberOfPlayers() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMBEROFPLAYERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, String, Boolean, String>> getPlayer(BigInteger id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, String, Boolean, String>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, String, Boolean, String>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, String, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, String, Boolean, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (String) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<List> getPlayersOfAgent() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPLAYERSOFAGENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updatePlayer(BigInteger id, Boolean forSale) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.Bool(forSale)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SoccerManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SoccerManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SoccerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SoccerManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SoccerManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SoccerManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SoccerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SoccerManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SoccerManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SoccerManager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<SoccerManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SoccerManager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SoccerManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SoccerManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SoccerManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SoccerManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class PlayerAddedEventResponse extends BaseEventResponse {
        public BigInteger playerId;

        public String name;

        public BigInteger price;

        public Boolean forSale;
    }

    public static class PlayerBoughtEventResponse extends BaseEventResponse {
        public BigInteger playerId;

        public String oldAgent;

        public String newAgent;

        public BigInteger price;
    }

    public static class PlayerUpdatedEventResponse extends BaseEventResponse {
        public BigInteger playerId;

        public Boolean oldForSaleValue;

        public Boolean newForSaleValue;
    }
}
