package com.mingle.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzz40500 on 15/10/18.
 */
public class AnimatorCompat {


    private List<ZAnimator> mObjectAnimators = new ArrayList<>();


    private List<ZAnimator> mOriginObjectAnimtor=mObjectAnimators;
    private boolean sequentially;
    private AnimatorSet animatorSet;


    public static AnimatorStream.ZObjectAnimatorStream animate(Object o) {

        AnimatorCompat animatorCompat=new AnimatorCompat();

        return new AnimatorStream(animatorCompat).animate(o);
    }

    public static AnimatorStream.ZValueAnimatorStream ofArgb(int arg) {

        AnimatorCompat animatorCompat=new AnimatorCompat();

        return  new AnimatorStream(animatorCompat).ofArgb(arg);
    }
    public static AnimatorStream.ZValueAnimatorStream ofInt (int arg) {

        AnimatorCompat animatorCompat=new AnimatorCompat();

        return new AnimatorStream(animatorCompat).ofInt(arg);
    }

    public  static AnimatorStream.ZValueAnimatorStream ofFloat(float... arg){
        AnimatorCompat animatorCompat=new AnimatorCompat();

        return  new AnimatorStream(animatorCompat).ofFloat(arg);

    }


    protected void addZAnimator(ZAnimator zObjectAnimator){
        mObjectAnimators.add(zObjectAnimator);
    }


    protected List<ZAnimator> getObjectAnimators() {
        return mObjectAnimators;
    }

    public void start(){


        build().start();
    }

    public AnimatorSet build(){
        List<Animator> dAnimatorSets=new ArrayList<>();
        List<Animator> animatorSets=new ArrayList<>();

        for (int i = 0; i < mObjectAnimators.size(); i++) {
            ZAnimator animator=mObjectAnimators.get(i);
            if(animator.isSequentially() && animatorSets.size()>0){
                dAnimatorSets.add(animTogetherFormAnimatorList(animatorSets));
                animatorSets=new ArrayList<>();
            }
            animatorSets.add(animator.getAnimator());

            if(i==mObjectAnimators.size()-1){
                dAnimatorSets.add(animTogetherFormAnimatorList(animatorSets));
            }
        }
       return   animSequentiallyFormAnimatorList(dAnimatorSets);

    }


    private AnimatorSet animSequentiallyFormAnimatorList(List<Animator> animatorSets) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] anims=arrayAnimFromList(animatorSets);
        animatorSet.playSequentially(anims);
        return animatorSet;
    }

    private AnimatorSet animTogetherFormAnimatorList(List<Animator> animatorSets) {

        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] anims=arrayAnimFromList(animatorSets);

        animatorSet.playTogether(anims);

        return animatorSet;
    }
    private Animator[] arrayAnimFromList(List<Animator> animatorSets){
        Animator[] anims=new Animator[animatorSets.size()];

        for (int i = 0; i < animatorSets.size(); i++) {
            anims[i]=animatorSets.get(i);
        }
        return  anims;

    }

    protected boolean isSequentially() {
        return sequentially;
    }

    protected void setSequentially(boolean sequentially) {
        this.sequentially = sequentially;
    }

    public void addAllZAnimator(List<ZAnimator> objectAnimators) {

        mObjectAnimators.addAll(objectAnimators);
    }
}
