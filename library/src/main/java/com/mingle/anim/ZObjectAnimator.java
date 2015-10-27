package com.mingle.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzz40500 on 15/10/18.
 */
public class ZObjectAnimator implements ZAnimator {


    private boolean isSeparate;

    private List<PropertyValuesHolder> mPropertyValuesHolderList;

    private List<ValueAnimator> mAnimatorList = new ArrayList<>();

    private ObjectAnimator mAnimator;

    public boolean isSequentially = false;

    private Object mTarget;

    private AnimListenerAdapter mAnimListenerAdapter;

    public ZObjectAnimator(Object o) {
        mTarget = o;
        mAnimator = newObjectAnimator();
    }

    public void anim(String propertyValues, float... arg) {

        addAnim(PropertyValuesHolder.ofFloat(propertyValues, arg));
    }


    private ObjectAnimator newObjectAnimator() {

        mAnimListenerAdapter = new AnimListenerAdapter();
        mPropertyValuesHolderList = new ArrayList<>();
        ObjectAnimator animation = new ObjectAnimator();
        animation.setTarget(mTarget);
        animation.addListener(mAnimListenerAdapter);
        return animation;
    }


    public void alpha(float... arg) {

        addAnim(PropertyValuesHolder.ofFloat("alpha", arg));
    }


    public void y(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("y", arg));
    }

    public void x(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("x", arg));
    }

    public void scaleX(float... arg) {

        addAnim(PropertyValuesHolder.ofFloat("scaleX", arg));
    }

    public void scaleY(float... arg) {

        addAnim(PropertyValuesHolder.ofFloat("scaleY", arg));
    }
    public void translationX(float... arg) {

        addAnim(PropertyValuesHolder.ofFloat("translationX", arg));
    }

    public void translationY(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("translationY", arg));
    }


    public void translationZ(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("translationZ", arg));
    }

    public void rotation(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("rotation", arg));
    }


    public void rotationY(float... arg) {

        addAnim(PropertyValuesHolder.ofFloat("rotationY", arg));
    }

    public void rotationX(float... arg) {
        addAnim(PropertyValuesHolder.ofFloat("rotationX", arg));
    }

    private void addAnim(PropertyValuesHolder anim) {

        if (isSeparate) {
            ObjectAnimator objectAnimator = build();
            mAnimator = newObjectAnimator();
            mAnimatorList.add(objectAnimator);
        }
        mPropertyValuesHolderList.add(anim);
    }

    public void addListener(Animator.AnimatorListener animatorListener) {
        isSeparate = true;
        mAnimator.addListener(animatorListener);
    }


    /**
     * 使用这个会下面生成的Animator 会拆分成2个Animator .放在AnimatorSet 中.
     *
     * @param duration
     * @return
     */
    public void setDuration(long duration) {
        isSeparate = true;
        mAnimator.setDuration(duration);
    }

    public void setInterpolator(TimeInterpolator interpolator) {


        isSeparate = true;
        mAnimator.setInterpolator(interpolator);
    }

    public void setStartDelay(long startDelay) {
        isSeparate = true;
        mAnimator.setStartDelay(startDelay);
    }

    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorListener) {

        isSeparate = true;
        mAnimator.addUpdateListener(animatorListener);
    }

    private ObjectAnimator build() {
        PropertyValuesHolder[] propertyValuesHolders = new PropertyValuesHolder[mPropertyValuesHolderList.size()];
        for (int i = 0; i < mPropertyValuesHolderList.size(); i++) {
            propertyValuesHolders[i] = mPropertyValuesHolderList.get(i);
        }
        mAnimator.setValues(propertyValuesHolders);
        ObjectAnimator anim = mAnimator;
        return anim;
    }


    public void withStartAction(Runnable runnable) {

        mAnimListenerAdapter.startAction = runnable;
    }

    public void withEndAction(Runnable runnable) {
        mAnimListenerAdapter.endAction = runnable;
    }

    @Override
    public boolean isSequentially() {
        return isSequentially;
    }

    @Override
    public void setSequentially(boolean isSequentially) {

        this.isSequentially = isSequentially;
    }

    public Animator getAnimator() {

        ObjectAnimator objectAnimator = build();
        if (mAnimatorList.size() > 0) {
            mAnimatorList.add(objectAnimator);
            AnimatorSet animatorSet = new AnimatorSet();
            Animator[] animators = new Animator[mAnimatorList.size()];
            for (int i = 0; i < mAnimatorList.size(); i++) {
                animators[i] = mAnimatorList.get(i);
            }
            animatorSet.playTogether(animators);
            animatorSet.start();
            return animatorSet;
        } else {
            return mAnimator;
        }

    }


}
