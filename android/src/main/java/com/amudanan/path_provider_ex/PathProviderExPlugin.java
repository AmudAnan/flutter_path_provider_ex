package com.amudanan.path_provider_ex;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** PathProviderExPlugin */
public class PathProviderExPlugin implements MethodCallHandler, FlutterPlugin {
  private MethodChannel channel;
  private static final String CHANNEL_NAME = "path_provider_ex";
  private Context applicationContext;

  @Override
  public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
    applicationContext = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL_NAME);
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onDetachedFromEngine(FlutterPluginBinding flutterPluginBinding) {
    channel.setMethodCallHandler(null);
    applicationContext = null;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getExtStorageData")) {
      ArrayList<HashMap> reply = StorageUtils.getExternalStorageAvailableData(applicationContext);
      result.success(reply);

    } else {
      result.notImplemented();
    }
  }
}

// Map<String, Object> reply = new HashMap<>();
// ArrayList<String> paths =
// StorageUtils.getExternalFilePaths(getApplicationContext());
// reply.put("paths", paths);
// result.success(reply);