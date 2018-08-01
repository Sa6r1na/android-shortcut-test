package com.example.lingbei.android_shortcut_test.data;

import com.example.lingbei.android_shortcut_test.data.Note;

import java.util.List;

public interface NoteServiceApi {

    interface NoteServiceCallback<T>{
        void onLoaded(T notes);
    }

    void getAllNotes(NoteServiceCallback<List<Note>> callback);

    void getNote(String noteId,NoteServiceCallback<Note>  callback);

    void saveNote(Note note);
}
