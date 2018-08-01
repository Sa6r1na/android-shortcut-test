package com.example.lingbei.android_shortcut_test.data;

import com.example.lingbei.android_shortcut_test.data.Note;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 接受note数据的主要入口点
 * 此处应该多学下设计模式
 */
public interface NoteRepository {
    interface LoadNotesCallback{
        void onNotesLoaded(List<Note> notes);
    }


    interface GetNoteCallback{
        void onNoteLoaded(Note note);
    }

    void getNotes(@Nullable LoadNotesCallback callback);

    void getNote(@Nullable String noteId,@Nullable GetNoteCallback callback);

    void saveNote(@Nullable Note note);

    void refreshData();
}
