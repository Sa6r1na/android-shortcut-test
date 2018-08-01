package com.example.lingbei.android_shortcut_test.data;

import android.support.annotation.NonNull;

public class NoteRepositories {
    public NoteRepositories(){

    }

    private static NoteRepositories  repository =null;

    public synchronized static NoteRepository getInMemoryRepoInstanse(@NonNull NoteServiceApi noteServiceApi){

        if (null == repository){
            repository = new In
        }

    }
}
