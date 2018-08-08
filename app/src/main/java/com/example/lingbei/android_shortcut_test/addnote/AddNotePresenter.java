package com.example.lingbei.android_shortcut_test.addnote;

import android.support.annotation.NonNull;

import com.example.lingbei.android_shortcut_test.data.Note;
import com.example.lingbei.android_shortcut_test.data.NoteRepository;
import com.example.lingbei.android_shortcut_test.util.ImageFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNotePresenter implements AddNoteContract.UserActionsListener{
    @NonNull
    private final NoteRepository mNoteRepository;


    @NonNull
    private  final AddNoteContract.View mAddNoteView;

    @NonNull
    private final ImageFile mImageFile;

    public AddNotePresenter(@NonNull NoteRepository noteRepository,
                            @NonNull  AddNoteContract.View addNoteView,
                            @NonNull ImageFile imageFile){
        mNoteRepository = noteRepository;
        mAddNoteView = addNoteView;
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title,String description){
        String imageURl = null;
        if(mImageFile.exists()){
            imageURl = mImageFile.getPath();
        }

        Note newNote = new Note(title,description,imageURl);
        if (newNote.isEmpty()) {
            mAddNoteView.showEmptyNoteError();
        }else {
            mNoteRepository.saveNote(newNote);
            mAddNoteView.showNotesList();
        }
    }


    @Override
    public void takePicture() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG"+timeStamp+"_";
        mImageFile.create(imageFileName,".jpg");
        mAddNoteView.openCamera(mImageFile.getPath());
    }


    @Override
    public void imageAvailable(){
        if (mImageFile.exists()){
            mAddNoteView.showImagePreview(mImageFile.getPath());
        }else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed(){captureFailed();}


    private void captureFailed(){
        mImageFile.delete();
        mAddNoteView.showEmptyNoteError();
    }

}
