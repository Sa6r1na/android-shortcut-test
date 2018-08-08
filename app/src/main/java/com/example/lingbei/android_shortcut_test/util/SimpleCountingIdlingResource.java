package com.example.lingbei.android_shortcut_test.util;


import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 相当于是为测试准备的一个类，如果计数是0证明当前是闲置状态，如果非0则是非闲置状态。
 * 这个类相当于访问UI的时候block住测试
 */
public final class SimpleCountingIdlingResource implements IdlingResource{
    private final String mResourceName;
    private final AtomicInteger counter = new AtomicInteger(0);

    // written from main thread, read from any thread.
    private volatile ResourceCallback  resourceCallback;

    public SimpleCountingIdlingResource(String resourcenName){
        mResourceName = resourcenName;
    }


    @Override
    public String getName() {
        return mResourceName;
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    public void increment(){
        counter.getAndIncrement();
    }

    public void decrement(){
        int counterVal = counter.decrementAndGet();
        if (counterVal ==0){
            if (null!=resourceCallback){
                resourceCallback.onTransitionToIdle();
            }
        }
        if (counterVal<0){
            throw new IllegalArgumentException("Counter has been corrupted!");
        }
    }


}
