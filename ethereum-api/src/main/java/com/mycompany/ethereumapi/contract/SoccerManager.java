package com.mycompany.ethereumapi.contract;

import io.reactivex.Flowable;
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
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
 * <p>Generated with web3j version 4.0.1.
 */
public class SoccerManager extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b5060008054600160a060020a03191633179055620000376401000000006200003d810204565b62000460565b620000f76040805190810160405280600581526020017f5072617373000000000000000000000000000000000000000000000000000000815250670de0b6b3a7640000608060405190810160405280605c81526020016000805160206200153a83398151915281526020016000805160206200155a83398151915281526020017f39335f4b5564786e6c6a5138794a715f6f726967696e616c2e6a7067000000008152503362000270640100000000026401000000009004565b50620001b26040805190810160405280600481526020017f44756475000000000000000000000000000000000000000000000000000000008152506729a2241af62c0000608060405190810160405280605d81526020016000805160206200153a83398151915281526020016000805160206200155a83398151915281526020017f3537385f76767a70306e414b4c706d575f6f726967696e616c2e6a70670000008152503362000270640100000000026401000000009004565b506200026d6040805190810160405280600b81526020017f46656c697065204d656c6f000000000000000000000000000000000000000000815250671bc16d674ec80000608060405190810160405280605d81526020016000805160206200153a83398151915281526020016000805160206200155a83398151915281526020017f3932335f775a314150785168665a53655f6f726967696e616c2e6a70670000008152503362000270640100000000026401000000009004565b50565b6001805481018082556040805160e08101825282815260208082018581528284018a8152606084018a90526080840189905260a08401879052600160a060020a03881660c085015260008681526002808552958120855181559251978301805460ff19169815159890981790975551805193949193620002f993928501929190910190620003bb565b50606082015160038201556080820151805162000321916004840191602090910190620003bb565b5060a0820151600591909101805460c09093015160ff199093169115159190911761010060a860020a031916610100600160a060020a03938416021790556004805460018181019092557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b018390559316600090815260036020908152604082208054958601815582529020909201829055509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620003fe57805160ff19168380011785556200042e565b828001600101855582156200042e579182015b828111156200042e57825182559160200191906001019062000411565b506200043c92915062000440565b5090565b6200045d91905b808211156200043c576000815560010162000447565b90565b6110ca80620004706000396000f3fe608060405260043610610071577c010000000000000000000000000000000000000000000000000000000060003504635a9ffb7981146100765780637de81fca146100db578063aab48c521461010c578063e55ae4e814610260578063fc94f54014610389578063fd6673f5146103bb575b600080fd5b34801561008257600080fd5b5061008b6103d0565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156100c75781810151838201526020016100af565b505050509050019250505060405180910390f35b6100f8600480360360208110156100f157600080fd5b5035610432565b604080519115158252519081900360200190f35b34801561011857600080fd5b5061024e6004803603606081101561012f57600080fd5b81019060208101813564010000000081111561014a57600080fd5b82018360208201111561015c57600080fd5b8035906020019184600183028401116401000000008311171561017e57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929584359590949093506040810192506020013590506401000000008111156101d957600080fd5b8201836020820111156101eb57600080fd5b8035906020019184600183028401116401000000008311171561020d57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506107ab945050505050565b60408051918252519081900360200190f35b34801561026c57600080fd5b5061028a6004803603602081101561028357600080fd5b50356109b3565b6040805160208082018790528415156060830152600160a060020a038416608083015260a08083528851908301528751919283929083019160c0840191908a019080838360005b838110156102e95781810151838201526020016102d1565b50505050905090810190601f1680156103165780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b83811015610349578181015183820152602001610331565b50505050905090810190601f1680156103765780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561039557600080fd5b506100f8600480360360408110156103ac57600080fd5b50803590602001351515610bb5565b3480156103c757600080fd5b5061024e610df2565b3360009081526003602090815260409182902080548351818402810184019094528084526060939283018282801561042757602002820191906000526020600020905b815481526020019060010190808311610413575b505050505090505b90565b60008181526002602052604081206001015460ff16151561049d576040805160e560020a62461bcd02815260206004820152601460248201527f506c6179657220646f65736e2774206578697374000000000000000000000000604482015290519081900360640190fd5b60008281526002602052604090206005015460ff161515610508576040805160e560020a62461bcd02815260206004820152601660248201527f506c61796572206973206e6f7420666f722073616c6500000000000000000000604482015290519081900360640190fd5b6000828152600260205260409020600501546101009004600160a060020a031633141561057f576040805160e560020a62461bcd02815260206004820181905260248201527f596f752061726520616c72656164792074686520706c61796572206167656e74604482015290519081900360640190fd5b60008281526002602052604090206003015434146105e7576040805160e560020a62461bcd02815260206004820152601860248201527f416d6f756e742073656e7420697320696e636f72726563740000000000000000604482015290519081900360640190fd5b60008281526002602052604090206003015433311015610677576040805160e560020a62461bcd02815260206004820152602c60248201527f596f7520646f6e2774206861766520656e6f75676874206574686572206f6e2060448201527f796f75722062616c616e63650000000000000000000000000000000000000000606482015290519081900360840190fd5b600082815260026020908152604080832060050180543361010090810274ffffffffffffffffffffffffffffffffffffffff00198316179283905591829004600160a060020a039081168652600385528386208054600181018255908752948620909401879055049190911680835291206106f8908463ffffffff610df816565b50600083815260026020526040808220600301549051600160a060020a0384169282156108fc02929190818181858888f1935050505015801561073f573d6000803e3d6000fd5b50600083815260026020908152604091829020600301548251868152600160a060020a038516928101929092523382840152606082015290517f87e04f5f6a6261cdd2f775831d9ffe91bb9cacb100d78a01bba93e0be84c18069181900360800190a150600192915050565b60008054600160a060020a0316331461080e576040805160e560020a62461bcd02815260206004820152601e60248201527f596f7520617265206e6f742074686520636f6e7472616374206f776e65720000604482015290519081900360640190fd5b835160001061088d576040805160e560020a62461bcd02815260206004820152602360248201527f54686520617267756d656e7420276e616d65272063616e6e6f7420626520656d60448201527f7074790000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b600083116108e5576040805160e560020a62461bcd02815260206004820181905260248201527f54686520617267756d656e742027707269636527206d757374206265203e2030604482015290519081900360640190fd5b60006108f385858533610e89565b90507f0c67b919d648c2b4bd260225fe9b485cc70a7e619047dcf5bb598ae38a0b72888186866001604051808581526020018060200184815260200183151515158152602001828103825285818151815260200191508051906020019080838360005b8381101561096e578181015183820152602001610956565b50505050905090810190601f16801561099b5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a1949350505050565b6000818152600260205260408120600101546060919082908290819060ff161515610a4e576040805160e560020a62461bcd02815260206004820152602960248201527f506c6179657220776974682074686520696e666f726d656420696420646f657360448201527f6e27742065786973740000000000000000000000000000000000000000000000606482015290519081900360840190fd5b6000868152600260208181526040928390206003810154600582015482850180548751610100600183161581026000190190921697909704601f8101879004870288018701909852878752939690959294600488019460ff8416949304600160a060020a031692918791830182828015610b095780601f10610ade57610100808354040283529160200191610b09565b820191906000526020600020905b815481529060010190602001808311610aec57829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959a5088945092508401905082828015610b975780601f10610b6c57610100808354040283529160200191610b97565b820191906000526020600020905b815481529060010190602001808311610b7a57829003601f168201915b50505050509250809050955095509550955095505091939590929450565b60008281526002602052604081206001015460ff161515610c46576040805160e560020a62461bcd02815260206004820152602960248201527f506c6179657220776974682074686520696e666f726d656420696420646f657360448201527f6e27742065786973740000000000000000000000000000000000000000000000606482015290519081900360840190fd5b60008381526002602052604090206005015460ff1615158215151415610cdc576040805160e560020a62461bcd02815260206004820152602d60248201527f506c6179657220666f7253616c6520737461747573206973207468652073616d60448201527f6520616c72656164792073657400000000000000000000000000000000000000606482015290519081900360840190fd5b6000838152600260205260409020600501546101009004600160a060020a03163314610d78576040805160e560020a62461bcd02815260206004820152602360248201527f596f7520617265206e6f7420746865206167656e74206f662074686520706c6160448201527f7965720000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b600083815260026020908152604091829020600501805485151560ff1982168117909255835187815260ff9091168015159382019390935280840191909152915190917f47116610d83e5898152286936a924c1198dab9b12f87e2eafc3bc39a14570f98919081900360600190a160019150505b92915050565b60045490565b6000805b8354811015610e7f57828482815481101515610e1457fe5b90600052602060002001541415610e7757835484906000198101908110610e3757fe5b90600052602060002001548482815481101515610e5057fe5b6000918252602090912001558354610e6c856000198301610fdd565b506001915050610dec565b600101610dfc565b5060009392505050565b6001805481018082556040805160e08101825282815260208082018581528284018a8152606084018a90526080840189905260a08401879052600160a060020a03881660c085015260008681526002808552958120855181559251978301805460ff19169815159890981790975551805193949193610f1093928501929190910190611006565b506060820151600382015560808201518051610f36916004840191602090910190611006565b5060a0820151600591909101805460c09093015160ff199093169115159190911774ffffffffffffffffffffffffffffffffffffffff001916610100600160a060020a03938416021790556004805460018181019092557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b018390559316600090815260036020908152604082208054958601815582529020909201829055509392505050565b81548183558181111561100157600083815260209020611001918101908301611084565b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061104757805160ff1916838001178555611074565b82800160010185558215611074579182015b82811115611074578251825591602001919060010190611059565b50611080929150611084565b5090565b61042f91905b80821115611080576000815560010161108a56fea165627a7a723058207f32cd77026c7d08cf7564031bb7982701ad8b03615521d8ba3bba2ae8da01610029687474703a2f2f7777772e70616c6d65697261732e636f6d2e62722f7075626c69632f75706c6f61642f696d6167656d2f656c656e636f732f696d6167656d5f";

    public static final String FUNC_GETAGENTPLAYERS = "getAgentPlayers";

    public static final String FUNC_BUYPLAYER = "buyPlayer";

    public static final String FUNC_ADDPLAYER = "addPlayer";

    public static final String FUNC_GETPLAYER = "getPlayer";

    public static final String FUNC_UPDATEPLAYER = "updatePlayer";

    public static final String FUNC_GETNUMBEROFPLAYERS = "getNumberOfPlayers";

    public static final Event PLAYERADDED_EVENT = new Event("PlayerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event PLAYERUPDATED_EVENT = new Event("PlayerUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event PLAYERBOUGHT_EVENT = new Event("PlayerBought", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteCall<List> getAgentPlayers() {
        final Function function = new Function(FUNC_GETAGENTPLAYERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> buyPlayer(BigInteger id, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> addPlayer(String name, BigInteger price, String image) {
        final Function function = new Function(
                FUNC_ADDPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.Utf8String(image)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<String, BigInteger, String, Boolean, String>> getPlayer(BigInteger id) {
        final Function function = new Function(FUNC_GETPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple5<String, BigInteger, String, Boolean, String>>(
                new Callable<Tuple5<String, BigInteger, String, Boolean, String>>() {
                    @Override
                    public Tuple5<String, BigInteger, String, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, BigInteger, String, Boolean, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updatePlayer(BigInteger id, Boolean forSale) {
        final Function function = new Function(
                FUNC_UPDATEPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.Bool(forSale)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getNumberOfPlayers() {
        final Function function = new Function(FUNC_GETNUMBEROFPLAYERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PlayerAddedEventResponse>() {
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
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PlayerUpdatedEventResponse>() {
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
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PlayerBoughtEventResponse>() {
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

    public static class PlayerAddedEventResponse {
        public Log log;

        public BigInteger playerId;

        public String name;

        public BigInteger price;

        public Boolean forSale;
    }

    public static class PlayerUpdatedEventResponse {
        public Log log;

        public BigInteger playerId;

        public Boolean oldForSaleValue;

        public Boolean newForSaleValue;
    }

    public static class PlayerBoughtEventResponse {
        public Log log;

        public BigInteger playerId;

        public String oldAgent;

        public String newAgent;

        public BigInteger price;
    }
}
