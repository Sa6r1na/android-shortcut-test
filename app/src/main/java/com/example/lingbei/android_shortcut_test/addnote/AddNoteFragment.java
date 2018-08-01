package com.example.lingbei.android_shortcut_test.addnote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNoteFragment extends Fragment implements AddNoteContract.View {
    public static final int REQUEST_CODE_IMAGE_CAPTURE =0x1001;
    private AddNoteContract.UserActionsListener  mActionListener;
    private TextView mTitle;
    private TextView mDescription;
    private ImageView mImageThumbnail;

    public static AddNoteFragment newInstance(){
        return new AddNoteFragment();
    }

    public AddNoteFragment(){
        //
    }


    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        mActionListener = new AddNoteP
    }
}
