package com.mingle.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/**
 * Created by zzz40500 on 15/10/19.
 */
public class AnimListenerAdapter extends AnimatorListenerAdapter{

    public  Runnable startAction;
    public  Runnable endAction;
    @Override
    public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        if(startAction !=null){
            startAction.run();
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        if(endAction !=null){
            endAction.run();
        }
    }
}
