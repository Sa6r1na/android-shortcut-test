package com.example.lingbei.android_shortcut_test.mock.util;

import com.example.lingbei.android_shortcut_test.util.ImageFileImpl;

import java.io.IOException;

public class FakeImageFileImpl extends ImageFileImpl {

    @Override
    public void create(String name,String extension) throws IOException{
        //什么也不做
    }

    @Override
    public String getPath(){
        return "file:///android_asset/atsl-logo.png";
    }

    @Override
    public boolean exists(){
        return true;
    }

}
