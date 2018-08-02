package com.example.lingbei.android_shortcut_test.data;

import com.google.common.collect.ImmutableList;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;


public class InMemoryNotesRepository implements NoteRepository {

    private final NoteServiceApi mNoteServiceApi;


    @VisibleForTesting
    List<Note>  mCachedNotes;

    public InMemoryNotesRepository(@NonNull  NoteServiceApi noteServiceApi){
        mNoteServiceApi = checkNotNull(noteServiceApi);
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback){
        checkNotNull(callback);
        if (mCachedNotes == null){
            mNoteServiceApi.getAllNotes((notes) -> {
                mCachedNotes = ImmutableList.copyOf(notes);
                callback.onNotesLoaded(mCachedNotes);
            });
        }else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void saveNote(@NonNull Note note){
        checkNotNull(note);
        mNoteServiceApi.saveNote(note);
        refreshData();
    }

    @Override
    public void getNote(@NonNull final String noteId,@NonNull final GetNoteCallback callback){
        checkNotNull(noteId);
        checkNotNull(callback);

        mNoteServiceApi.getNote(noteId, callback::onNoteLoaded);
    }

    @Override
    public void refreshData(){
        mCachedNotes = null;  //为什么设置了个null啊
    }
}
