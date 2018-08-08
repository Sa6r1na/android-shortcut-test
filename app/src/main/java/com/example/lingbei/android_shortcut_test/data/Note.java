package com.example.lingbei.android_shortcut_test.data;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public final class Note {
    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mImageUrl;

    public Note(@Nullable String title,@Nullable String description){
        this(title,description,null);
    }

    public Note(@Nullable String title,@Nullable String description,@Nullable String imageurl){
        mId= UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageurl;
    }

    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }


    public boolean isEmpty(){
        return (mTitle == null ||"".equals(mTitle))&&
                (mDescription ==null ||"".equals(mDescription));
    }

    //把equals方法重写了，对比两个note对象相等
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note)o;
        return Objects.equals(mId,note.mId) &&
                Objects.equals(mTitle,note.mTitle) &&
                Objects.equals(mDescription,note.mDescription) &&
                Objects.equals(mImageUrl,note.mImageUrl) ;
    }

    @Override
    public int hashCode(){
        return Objects.hash(mId,mTitle,mDescription,mImageUrl);
    }
}
