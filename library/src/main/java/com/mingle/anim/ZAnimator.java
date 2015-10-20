package com.mingle.anim;

import android.animation.Animator;

/**
 * Created by zzz40500 on 15/10/19.
 */
public interface ZAnimator {


    boolean isSequentially();

    void setSequentially(boolean isSequentially);

    Animator getAnimator();
}
