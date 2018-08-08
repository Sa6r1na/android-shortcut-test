package com.example.lingbei.android_shortcut_test.mock.data;

import android.support.annotation.VisibleForTesting;
import android.util.ArrayMap;

import com.example.lingbei.android_shortcut_test.data.Note;
import com.example.lingbei.android_shortcut_test.data.NoteServiceApi;
import com.google.common.collect.Lists;

import java.util.List;

public class FakeNotesServiceApiImpl implements NoteServiceApi{
    private static final ArrayMap<String,Note>  NOTES_SERVICE_DATA = new ArrayMap<>();
    @Override
    public void getAllNotes(NoteServiceCallback<List<Note>> callback){
        callback.onLoaded(Lists.newArrayList(NOTES_SERVICE_DATA.values()));
    }


    @Override
    public void getNote(String noteId,NoteServiceCallback<Note> callback){
        Note note = NOTES_SERVICE_DATA.get(noteId);
        callback.onLoaded(note);
    }

    @Override
    public void saveNote(Note note){
        NOTES_SERVICE_DATA.put(note.getId(),note);
    }

    @VisibleForTesting
    public static void addNotes(Note... notes){
        for (Note note:notes){
            NOTES_SERVICE_DATA.put(note.getId(),note);
        }
    }
}
