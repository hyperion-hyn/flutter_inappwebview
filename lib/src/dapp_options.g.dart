// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'dapp_options.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DappOptions _$DappOptionsFromJson(Map<String, dynamic> json) {
  return DappOptions(
    json['walletAddress'] as String,
    json['rpcUrl'] as String,
    json['chainId'] as int,
  );
}

Map<String, dynamic> _$DappOptionsToJson(DappOptions instance) =>
    <String, dynamic>{
      'walletAddress': instance.walletAddress,
      'rpcUrl': instance.rpcUrl,
      'chainId': instance.chainId,
    };
