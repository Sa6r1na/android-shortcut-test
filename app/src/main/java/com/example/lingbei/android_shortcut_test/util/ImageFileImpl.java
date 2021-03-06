package com.example.lingbei.android_shortcut_test.util;

import android.net.Uri;
import android.support.annotation.VisibleForTesting;

import java.io.File;
import java.io.IOException;

public class ImageFileImpl implements ImageFile {
    @VisibleForTesting
    File mImageFile;

    @Override
    public void create(String name, String extension) throws IOException {

    }

    @Override
    public boolean exists() {
        return null != mImageFile && mImageFile.exists();
    }

    @Override
    public void delete() {
        mImageFile = null;
    }

    @Override
    public String getPath() {
        return Uri.fromFile(mImageFile).toString();
    }
}
