package com.xiaoma.share.utils;

import java.io.File;
import java.lang.reflect.Field;

public class FileUtils {


    public static String getShareRootPath() {
        return getShareRootFile().getAbsolutePath();
    }

    public static File getShareRootFile() {
        return new File("");
    }


    public static boolean isInShareRootPath(File file) {
        if (file == null || !file.exists()) return false;
        return file.getAbsolutePath().startsWith(getShareRootPath());
    }


    public static File copyFile(File src, File dest) {

        return dest;
    }


    public static File copyToShareRoot(File src) {
        File shareRootFile = getShareRootFile();
        File dest = new File(shareRootFile, src.getName());

        return dest;
    }


}
