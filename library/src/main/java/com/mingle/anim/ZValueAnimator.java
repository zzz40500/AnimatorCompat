package com.mingle.anim;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

/**
 * Created by zzz40500 on 15/10/19.
 */
public class ZValueAnimator extends  ValueAnimator implements ZAnimator{


    private AnimListenerAdapter mAnimListenerAdapter;

    private boolean isSequentially;

    public ZValueAnimator( ) {
        super();
        mAnimListenerAdapter =new AnimListenerAdapter();
        this.addListener(mAnimListenerAdapter);
    }

    public static ZValueAnimator ofArgb(int... arg) {

        ZValueAnimator zV=    new ZValueAnimator();
        zV.setIntValues(arg);
        zV.setEvaluator(new ArgbEvaluator());
        return zV;
    }
    public static ZValueAnimator ofInt (int... arg) {

        ZValueAnimator zV=  new ZValueAnimator();
        zV.setIntValues(arg);
        return zV;
    }

    public  static ZValueAnimator ofFloat(float... arg){

        ZValueAnimator zV=    new ZValueAnimator();
        zV.setFloatValues(arg);
        return zV;

    }


    public ZValueAnimator withStartAction(Runnable runnable){
        mAnimListenerAdapter.startAction=runnable;
        return  this;
    }

    public ZValueAnimator withEndAction(Runnable runnable){

        mAnimListenerAdapter.endAction=runnable;

        return  this;

    }


    @Override
    public boolean isSequentially() {
        return isSequentially;
    }

    @Override
    public void setSequentially(boolean isSequentially) {

        this.isSequentially=isSequentially;
    }

    @Override
    public Animator getAnimator() {
        return this;
    }


}
