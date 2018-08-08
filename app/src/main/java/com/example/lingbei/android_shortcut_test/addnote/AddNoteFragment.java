package com.example.lingbei.android_shortcut_test.addnote;
import com.bumptech.glide.Glide;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.lingbei.android_shortcut_test.R;
import com.example.lingbei.android_shortcut_test.mock.Injection;
import com.example.lingbei.android_shortcut_test.util.EspressoIdlingResource;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkState;

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
        mActionListener = new AddNotePresenter(Injection.provideNotesRepository(), this,Injection.provideImageFile());

        FloatingActionButton  fab =
                getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(view -> mActionListener.saveNote(mTitle.getText().toString(),
                mDescription.getText().toString()));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_addnote,container,false);
        mTitle = root.findViewById(R.id.add_note_title);
        mDescription = root.findViewById(R.id.add_note_description);
        mImageThumbnail = root.findViewById(R.id.add_note_image_thumbnail);

        setHasOptionsMenu(true);
        //如果当前fragment不重建就不会调用onCreate，onDestroy不会被调用因为当前fragment被detach到Activity
        setRetainInstance(true);

        return root;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.take_picture:
                try {
                    mActionListener.takePicture();
                } catch (IOException e) {
                    if(getView()!=null){
                        Snackbar.make(getView(),getString(R.string.take_picture_error),
                                Snackbar.LENGTH_LONG).show();
                    }

                }
                return true;
        }
        return false;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.fragment_addnote_options_menu_actions,menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public void showEmptyNoteError() {
        Snackbar.make(mTitle, getString(R.string.empty_note_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNotesList(){
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void openCamera(String saveTo){
        Intent tackPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tackPictureIntent.resolveActivity(getContext().getPackageManager())!=null){
            tackPictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(saveTo));
            startActivityForResult(tackPictureIntent,REQUEST_CODE_IMAGE_CAPTURE);
        }else {
            Snackbar.make(mTitle,getString(R.string.cannot_connect_to_camera_message),
                    Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showImagePreview(@NonNull String imageUrl){
        //如果参数是false则返回IllegalStateException异常
        checkState(!TextUtils.isEmpty(imageUrl));

        EspressoIdlingResource.increment();

        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(mImageThumbnail){
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                               GlideAnimation<? super GlideDrawable> animation){
                        super.onResourceReady(resource,animation);
                        EspressoIdlingResource.decrement();
                    }
                });
    }


    @Override
    public void showImageError(){
        Snackbar.make(mTitle,getString(R.string.cannot_connect_to_camera_message),
                Snackbar.LENGTH_SHORT);
    }


    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data){
        if (REQUEST_CODE_IMAGE_CAPTURE == requestcode && Activity.RESULT_OK == resultcode){
            mActionListener.imageAvailable();
        }else {
            mActionListener.imageCaptureFailed();
        }
    }

}
