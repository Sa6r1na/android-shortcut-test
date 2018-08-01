package com.example.lingbei.android_shortcut_test.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;


public class InMemoryNotesRepository implements NoteRepository {

    private final NoteServiceApi mNoteServiceApi;


    @VisibleForTesting
    List<Note>  mCachedNotes;

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /***
     *
     *   public static <T> T checkNotNull(T reference) {
     if (reference == null) {
     throw new NullPointerException();
     }
     return reference;
     }
     * @param noteServiceApi
     */
    public InMemoryNotesRepository(@NonNull  NoteServiceApi noteServiceApi){
        mNoteServiceApi = checkNotNull(noteServiceApi);
    }
}
