package com.example.lingbei.android_shortcut_test.util;

import java.io.IOException;

public interface ImageFile {
    void create(String name, String extension) throws IOException;

    boolean exists();

    void delete();

    String getPath();
}
