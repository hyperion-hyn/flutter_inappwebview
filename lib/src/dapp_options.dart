part 'dapp_options.g.dart';

class DappOptions extends Object {

  String walletAddress;

  String rpcUrl;

  int chainId;

  DappOptions(this.walletAddress,this.rpcUrl,this.chainId,);

  factory DappOptions.fromJson(Map<String, dynamic> srcJson) => _$DappOptionsFromJson(srcJson);

  Map<String, dynamic> toJson() => _$DappOptionsToJson(this);

}