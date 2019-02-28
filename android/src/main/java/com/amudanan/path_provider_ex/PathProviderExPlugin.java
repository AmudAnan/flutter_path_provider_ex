package com.amudanan.path_provider_ex;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** PathProviderExPlugin */
public class PathProviderExPlugin implements MethodCallHandler {
  private final Registrar mRegistrar;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "path_provider_ex");
    PathProviderExPlugin instance = new PathProviderExPlugin(registrar);
    channel.setMethodCallHandler(instance);
  }

  private PathProviderExPlugin(Registrar registrar) {
    this.mRegistrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getExtStorageData")) {
      ArrayList<HashMap> reply = StorageUtils.getExternalStorageAvailableData(mRegistrar.context());
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