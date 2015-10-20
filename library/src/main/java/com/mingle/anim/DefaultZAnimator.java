package com.mingle.anim;

import android.animation.Animator;

/**
 * Created by zzz40500 on 15/10/20.
 */
public class DefaultZAnimator implements ZAnimator {

    private boolean isSequentially;

    private Animator mAnimator;

    public DefaultZAnimator(Animator animator) {
        mAnimator = animator;
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
        return mAnimator;
    }
}
