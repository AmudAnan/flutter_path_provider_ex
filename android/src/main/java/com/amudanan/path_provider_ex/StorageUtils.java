package com.amudanan.path_provider_ex;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import android.os.StatFs;

public class StorageUtils {
  static ArrayList<HashMap> getExternalStorageAvailableData(Context context) {
    final File[] appsDir = context.getExternalFilesDirs(null);
    final ArrayList<HashMap> extRootPaths = new ArrayList<>();
    for (final File file : appsDir) {
      String path = file.getAbsolutePath();
      StatFs statFs = new StatFs(path);
      long availableBytes = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
      HashMap<String, Object> storageData = new HashMap<>();
      try {
        String rootPath = file.getParentFile().getParentFile().getParentFile().getParentFile().getAbsolutePath();
        storageData.put("rootPath", rootPath);
      } catch (Exception e) {
      }
      storageData.put("path", path);
      storageData.put("availableBytes", availableBytes);
      extRootPaths.add(storageData);
    }
    return extRootPaths;
  }

  // static ArrayList getExternalFilePaths(Context context) {
  //   final File[] appsDir = context.getExternalFilesDirs(null);
  //   final ArrayList<String> extRootPaths = new ArrayList<>();
  //   for (final File file : appsDir)
  //     extRootPaths.add(file.getAbsolutePath());
  //   return extRootPaths;
  // }

  // static ArrayList<Long> getExternalStorageAvailableBytes(Context context) {
  //   final File[] appsDir = context.getExternalFilesDirs(null);
  //   final ArrayList<Long> extRootPaths = new ArrayList<>();
  //   for (final File file : appsDir) {
  //     StatFs statFs = new StatFs(file.getAbsolutePath());
  //     extRootPaths.add(statFs.getBlockCountLong() * statFs.getAvailableBlocksLong());
  //   }
  //   return extRootPaths;
  // }

}