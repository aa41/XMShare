package com.xiaoma.share.shareObj.convert;

import android.graphics.Bitmap;

import java.io.File;

public interface IImageConvert {
    File toFile();

    byte[] toBytes();

    Bitmap toBitmap();

    int toDrawable();

    String toAssetPath();
}
