package com.example.lingbei.android_shortcut_test.data;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class NoteRepositories {
    public NoteRepositories(){

    }
    //NoteRepository 刚开始写错了，找了半天，写成了NoteRepositories
    private static NoteRepository  repository =null;

    public synchronized static NoteRepository getInMemoryRepoInstanse(@NonNull NoteServiceApi noteServiceApi){
        checkNotNull(noteServiceApi);
        if (null == repository){
            repository = new InMemoryNotesRepository(noteServiceApi);
        }
        return  repository;

    }
}
