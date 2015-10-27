package com.mingle.anim;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

import java.util.List;

/**
 * Created by zzz40500 on 15/10/19.
 */
public class AnimatorStream {


    private AnimatorCompat mAnimatorCompat;

    public AnimatorStream(AnimatorCompat animatorCompat) {
        mAnimatorCompat = animatorCompat;
    }

    public void start() {
        mAnimatorCompat.start();
    }

    public Animator build(){

        return  mAnimatorCompat.build();
    }

    public ZObjectAnimatorStream animate(Object target) {


        return animate(target, false);
    }

    public ZObjectAnimatorStream animate(Object target, boolean isSequentially) {


        ZObjectAnimatorStream zObjectAnimatorStream = new ZObjectAnimatorStream(target);
        zObjectAnimatorStream.setSequentially(isSequentially);
        return zObjectAnimatorStream;
    }

    protected ZValueAnimatorStream ofArgb(boolean isSequentially, int... arg) {


        return new ZValueAnimatorStream().ofArgb(isSequentially, arg);
    }

    public ZValueAnimatorStream ofArgb(int... arg) {


        return ofArgb(false, arg);
    }

    protected ZValueAnimatorStream ofInt(boolean isSequentially, int... arg) {


        return new ZValueAnimatorStream().ofInt(isSequentially, arg);
    }

    public ZValueAnimatorStream ofInt(int... arg) {


        return ofInt(false, arg);
    }


    public ZValueAnimatorStream ofFloat(float... arg) {

        return ofFloat(false, arg);

    }

    protected ZValueAnimatorStream ofFloat(boolean isSequentially, float... arg) {

        return new ZValueAnimatorStream().ofFloat(isSequentially, arg);

    }


    public Bridge together(Stream zObjectAnimator) {
        List<ZAnimator> zAnimators = zObjectAnimator.getStream().getObjectAnimators();
        if (zAnimators.size() > 0) {
            mAnimatorCompat.addAllZAnimator(zAnimators);
        }
        return getStreamBridge();
    }
    public Bridge together(Animator animator) {

        if(animator != null){
            mAnimatorCompat.addZAnimator(new DefaultZAnimator(animator));
        }
        return getStreamBridge();
    }

    public Build together() {

        return new Build(this, mAnimatorCompat, false);
    }

    public Build sequentially() {

        return new Build(this, mAnimatorCompat, true);
    }

    public Bridge sequentially(Stream zObjectAnimator) {
        List<ZAnimator> zAnimators = zObjectAnimator.getStream().getObjectAnimators();
        if (zAnimators.size() > 0) {
            zAnimators.get(0).setSequentially(true);
            mAnimatorCompat.addAllZAnimator(zAnimators);
        }
        return getStreamBridge();
    }
    public Bridge sequentially(Animator animator) {
        if(animator != null){

            DefaultZAnimator defaultZAnimator =new DefaultZAnimator(animator);

            defaultZAnimator.setSequentially(true);
            mAnimatorCompat.addZAnimator(defaultZAnimator);
        }
        return getStreamBridge();
    }



    private Bridge mStreamBridge;

    public Bridge  getStreamBridge(){
        if(mStreamBridge==null){
            mStreamBridge=new Bridge();
        }
        return mStreamBridge;
    }

    public class Bridge{


        public Bridge together(Animator animator) {

            return AnimatorStream.this.together(animator);
        }
        public Bridge sequentially(Animator animator) {
            return AnimatorStream.this.sequentially(animator);
        }

        public Bridge together(Stream zObjectAnimator) {

            return AnimatorStream.this.together(zObjectAnimator);
        }

        public Build together() {

            return AnimatorStream.this.together();
        }

        public Build sequentially() {

            return AnimatorStream.this.sequentially();
        }

        public Bridge sequentially(Stream zObjectAnimator) {

            return AnimatorStream.this.sequentially(zObjectAnimator);
        }

    }


    public class  Stream{

        AnimatorCompat  getStream(){

            return  mAnimatorCompat;
        }
        public Bridge together(Animator animator) {

            return AnimatorStream.this.together(animator);
        }
        public Bridge sequentially(Animator animator) {
            return AnimatorStream.this.sequentially(animator);
        }

        public Bridge together(Stream zObjectAnimator) {

            return AnimatorStream.this.together(zObjectAnimator);
        }

        public Build together() {

            return AnimatorStream.this.together();
        }

        public Build sequentially() {

            return AnimatorStream.this.sequentially();
        }

        public Bridge sequentially(Stream zObjectAnimator) {

            return AnimatorStream.this.sequentially(zObjectAnimator);
        }

        public void start() {
            AnimatorStream.this.start();
        }

        public Animator build(){

            return   AnimatorStream.this.build();
        }

    }


    public class ZObjectAnimatorStream extends  Stream {


        private ZObjectAnimator mTargetAnimator;

        public ZObjectAnimatorStream(Object target) {
            mTargetAnimator = new ZObjectAnimator( target);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
        }

        protected void setSequentially(boolean isSequentially) {
            mTargetAnimator.isSequentially = isSequentially;
        }

        public ZObjectAnimatorStream anim(String propertyValues, float... arg) {
            mTargetAnimator.anim(propertyValues, arg);
            return  this;
        }


        public ZObjectAnimatorStream alpha(float... arg) {

            mTargetAnimator.alpha(arg);
            return this;
        }
       public ZObjectAnimatorStream scaleX(float... arg) {

            mTargetAnimator.scaleX(arg);
            return this;
        }
       public ZObjectAnimatorStream scaleY(float... arg) {

            mTargetAnimator.scaleY(arg);
            return this;
        }


        public ZObjectAnimatorStream translationX(float... arg) {
            mTargetAnimator.translationX(arg);
            return this;
        }

        public ZObjectAnimatorStream translationY(float... arg) {
            mTargetAnimator.translationY(arg);
            return this;
        }


        public ZObjectAnimatorStream translationZ(float... arg) {
            mTargetAnimator.translationZ(arg);

            return this;
        }

        public ZObjectAnimatorStream rotation(float... arg) {
            mTargetAnimator.rotation(arg);

            return this;
        }

        public ZObjectAnimatorStream x(float... arg){
            mTargetAnimator.x(arg);
            return this;

        }

        public ZObjectAnimatorStream y(float... arg){
            mTargetAnimator.y(arg);
            return this;

        }

        public ZObjectAnimatorStream rotationY(float... arg) {
            mTargetAnimator.rotationY(arg);

            return this;
        }

        public ZObjectAnimatorStream rotationX(float... arg) {
            mTargetAnimator.rotationX(arg);

            return this;
        }


        public ZObjectAnimatorStream addListener(Animator.AnimatorListener animatorListener) {
            mTargetAnimator.addListener(animatorListener);
            return this;
        }
        /**
         * 使用这个会下面生成的Animator 会拆分成2个Animator .放在AnimatorSet 中.
         *
         * @param duration
         * @return
         */
        public ZObjectAnimatorStream setDuration(long duration) {

            mTargetAnimator.setDuration(duration);

            return this;
        }

        public ZObjectAnimatorStream setInterpolator(TimeInterpolator interpolator) {

            mTargetAnimator.setInterpolator(interpolator);
            return this;
        }

        public ZObjectAnimatorStream setStartDelay(long startDelay) {
            mTargetAnimator.setStartDelay(startDelay);
            return this;
        }

        public ZObjectAnimatorStream addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorListener) {

            mTargetAnimator.addUpdateListener(animatorListener);
            return this;

        }


        public ZObjectAnimatorStream withStartAction(Runnable runnable) {

            mTargetAnimator.withStartAction(runnable);

            return this;
        }

        public ZObjectAnimatorStream withEndAction(Runnable runnable) {
            mTargetAnimator.withEndAction(runnable);

            return this;
        }




    }



    public class ZValueAnimatorStream extends  Stream {


        protected ZValueAnimator mTargetAnimator;


        public ZValueAnimatorStream( ) {

        }



        public ZValueAnimatorStream ofArgb(int... arg) {

            mTargetAnimator = ZValueAnimator.ofArgb( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
            return this.ofArgb(false, arg);
        }

        public ZValueAnimatorStream ofArgb(boolean isSequentially, int... arg) {

            mTargetAnimator = ZValueAnimator.ofArgb( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
            mTargetAnimator.setSequentially(isSequentially);
            return this;

        }

        public ZValueAnimatorStream ofInt(int... arg) {
            mTargetAnimator = ZValueAnimator.ofInt( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
            return this.ofInt(false, arg);

        }

        public ZValueAnimatorStream ofInt(boolean isSequentially, int... arg) {

            mTargetAnimator = ZValueAnimator.ofInt( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
            mTargetAnimator.setSequentially(isSequentially);
            return this;

        }

        public ZValueAnimatorStream ofFloat(float... arg) {
            mTargetAnimator = ZValueAnimator.ofFloat( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);

            return this.ofFloat(false, arg);
        }

        public ZValueAnimatorStream ofFloat(boolean isSequentially, float... arg) {
            mTargetAnimator = ZValueAnimator.ofFloat( arg);
            mAnimatorCompat.addZAnimator(mTargetAnimator);
            mTargetAnimator.setSequentially(isSequentially);
            return this;
        }

        public ZValueAnimatorStream addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
            mTargetAnimator.addUpdateListener(listener);
            return this;
        }

        /**
         * 使用这个会下面生成的Animator 会拆分成2个Animator .放在AnimatorSet 中.
         *
         * @param duration
         * @return
         */
        public ZValueAnimatorStream setDuration(long duration) {

            mTargetAnimator.setDuration(duration);

            return this;
        }

        public ZValueAnimatorStream setInterpolator(TimeInterpolator interpolator) {

            mTargetAnimator.setInterpolator(interpolator);
            return this;
        }

        public ZValueAnimatorStream setStartDelay(long startDelay) {
            mTargetAnimator.setStartDelay(startDelay);
            return this;
        }


        public ZValueAnimatorStream withStartAction(Runnable runnable) {

            mTargetAnimator.withStartAction(runnable);
            return this;
        }

        public ZValueAnimatorStream withEndAction(Runnable runnable) {

            mTargetAnimator.withEndAction(runnable);
            return this;

        }



    }

    public class Build {

        public boolean isSequentially;

        public AnimatorCompat mAnimatorCompat;

        private AnimatorStream mAnimatorStream;


        public Build(AnimatorStream animatorStream, AnimatorCompat animatorCompat, boolean isSequentially) {
            this.isSequentially = isSequentially;
            this.mAnimatorStream = animatorStream;
            mAnimatorCompat = animatorCompat;
        }

        public ZObjectAnimatorStream animate(Object target) {


            return mAnimatorStream.animate(target, isSequentially);
        }

        public ZValueAnimatorStream ofArgb(int... arg) {


            return mAnimatorStream.ofArgb(isSequentially, arg);
        }

        public ZValueAnimatorStream ofInt(int... arg) {


            return mAnimatorStream.ofInt(isSequentially, arg);
        }

        public ZValueAnimatorStream ofFloat(float... arg) {

            return mAnimatorStream.ofFloat(isSequentially, arg);

        }

    }


}
