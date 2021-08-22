package com.xiaoma.share.shareObj;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.xiaoma.share.shareObj.convert.IImageConvert;
import com.xiaoma.share.utils.FileUtils;

import java.io.File;

public class ImageObj extends ShareObj implements IImageConvert {

    protected IImageConvert convert;

    public ImageObj(File file) {
        initConvert(file);
    }

    public ImageObj(String assetPath) {
        initConvert(assetPath);
    }

    public ImageObj(Bitmap bitmap) {
        initConvert(bitmap);
    }

    public ImageObj(int drawableId) {
        initConvert(drawableId);
    }

    public ImageObj(byte[] bytes) {
        initConvert(bytes);
    }


    public void initConvert(Object o) {
        if (o instanceof File) {
            convert = new FileConvert((File) o);
        } else if (o instanceof Bitmap) {
            convert = new BitmapConvert((Bitmap) o);
        } else if (o instanceof Integer) {
            convert = new DrawableImageConvert((Integer) o);
        } else if (o instanceof String) {
            convert = new AssetCovert((String) o);
        } else if (o instanceof byte[]) {
            convert = new BytesConvert((byte[]) o);
        } else {
            throw new IllegalArgumentException("不支持该类型的分享");
        }


    }

    @Override
    public File toFile() {
        return convert.toFile();
    }

    @Override
    public byte[] toBytes() {
        return convert.toBytes();
    }

    @Override
    public Bitmap toBitmap() {
        return convert.toBitmap();
    }

    @Override
    public int toDrawable() {
        return convert.toDrawable();
    }

    @Override
    public String toAssetPath() {
        return convert.toAssetPath();
    }


    public abstract static class AbsImageConvert implements IImageConvert {
        protected File file;
        protected byte[] bytes;
        protected Bitmap bitmap;
        protected int drawableId;
        protected String assetPath;

        @Override
        public File toFile() {
            if (file == null || !file.exists()) {
                file = asFile();
            }
            return file;
        }

        @Override
        public byte[] toBytes() {
            if (bytes == null || bytes.length <= 0) {
                bytes = asBytes();
            }
            return bytes;
        }

        @Override
        public Bitmap toBitmap() {
            if (bitmap == null || bitmap.isRecycled()) {
                bitmap = asBitmap();
            }
            return bitmap;
        }


        @Override
        public int toDrawable() {
            if (drawableId <= 0) {
                drawableId = asDrawable();
            }
            return drawableId;
        }

        @Override
        public String toAssetPath() {
            if (TextUtils.isEmpty(assetPath)) {
                assetPath = asAssetPath();
            }
            return assetPath;
        }

        protected abstract String asAssetPath();

        protected abstract int asDrawable();

        protected abstract Bitmap asBitmap();

        protected abstract byte[] asBytes();

        public abstract File asFile();
    }


    public class BitmapConvert extends AbsImageConvert {

        public BitmapConvert(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected String asAssetPath() {
            return null;
        }

        @Override
        protected int asDrawable() {
            return 0;
        }

        @Override
        protected Bitmap asBitmap() {
            return bitmap;
        }

        @Override
        protected byte[] asBytes() {
            return new byte[0];
        }

        @Override
        public File asFile() {
            return null;
        }
    }


    public class FileConvert extends AbsImageConvert {

        public FileConvert(File file) {
            this.file = file;
        }

        @Override
        protected String asAssetPath() {
            return null;
        }

        @Override
        protected int asDrawable() {
            return 0;
        }

        @Override
        protected Bitmap asBitmap() {
            return null;
        }

        @Override
        protected byte[] asBytes() {
            return new byte[0];
        }

        @Override
        public File asFile() {
            if (!FileUtils.isInShareRootPath(file)) {
                file = FileUtils.copyToShareRoot(file);
            }
            return file;
        }
    }


    public class BytesConvert extends AbsImageConvert {
        public BytesConvert(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override
        protected String asAssetPath() {
            return null;
        }

        @Override
        protected int asDrawable() {
            return 0;
        }

        @Override
        protected Bitmap asBitmap() {
            return null;
        }

        @Override
        protected byte[] asBytes() {
            return bytes;
        }

        @Override
        public File asFile() {
            return null;
        }
    }


    public class DrawableImageConvert extends AbsImageConvert {


        public DrawableImageConvert(int drawableId) {
            this.drawableId = drawableId;
        }

        @Override
        protected String asAssetPath() {
            return null;
        }

        @Override
        protected int asDrawable() {
            return drawableId;
        }

        @Override
        protected Bitmap asBitmap() {
            return null;
        }

        @Override
        protected byte[] asBytes() {
            return new byte[0];
        }

        @Override
        public File asFile() {
            return null;
        }
    }

    public class AssetCovert extends AbsImageConvert {
        public AssetCovert(String assetPath) {
            this.assetPath = assetPath;
        }

        @Override
        protected String asAssetPath() {
            return assetPath;
        }

        @Override
        protected int asDrawable() {
            return 0;
        }

        @Override
        protected Bitmap asBitmap() {
            return null;
        }

        @Override
        protected byte[] asBytes() {
            return new byte[0];
        }

        @Override
        public File asFile() {
            return null;
        }
    }
}
