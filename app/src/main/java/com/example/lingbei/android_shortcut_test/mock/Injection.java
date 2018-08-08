package com.example.lingbei.android_shortcut_test.mock;

import com.example.lingbei.android_shortcut_test.data.NoteRepositories;
import com.example.lingbei.android_shortcut_test.data.NoteRepository;
import com.example.lingbei.android_shortcut_test.mock.data.FakeNotesServiceApiImpl;
import com.example.lingbei.android_shortcut_test.mock.util.FakeImageFileImpl;
import com.example.lingbei.android_shortcut_test.util.ImageFile;

public class Injection {

    public static ImageFile provideImageFile(){
        return new FakeImageFileImpl();
    }

    public static NoteRepository provideNotesRepository(){
        return NoteRepositories.getInMemoryRepoInstanse(new FakeNotesServiceApiImpl());
    }
}
