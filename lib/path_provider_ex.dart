import 'dart:async';
import 'dart:math';

import 'package:flutter/services.dart';

class PathProviderEx {
  static const MethodChannel _channel = const MethodChannel('path_provider_ex');

  // static Future<String> get platformVersion async {
  //   final String version = await _channel.invokeMethod('getPlatformVersion');
  //   return version;
  // }

  static Future<List<StorageInfo>> getStorageInfo() async {
    List reply = await _channel.invokeMethod('getExtStorageData', []);
    List<StorageInfo> storageInfos = reply
        .map((storageInfoMap) => StorageInfo.fromJson(storageInfoMap))
        .toList();

    return storageInfos;
  }
}

class StorageInfo {
  final String appFilesDir;
  final String rootDir;
  final int availableBytes;
  int get availableGB => availableBytes ~/ pow(2, 30);
  int get availableMB => availableBytes ~/ pow(2, 20);
  StorageInfo(this.appFilesDir, this.rootDir, this.availableBytes);
  factory StorageInfo.fromJson(Map json) {
    return StorageInfo(json["path"], json["rootPath"], json["availableBytes"]);
  }
  //get root with: reply[0]["path"].split("/").sublist(0,reply[0]["path"].split("/").length-4).join("/");
}
