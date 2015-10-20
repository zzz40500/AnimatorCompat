# AnimatorCompat
AnimatorCompat: 一个快速创建动画帮助类   
[github](https://github.com/zzz40500/AnimatorCompat)

#前言 
![bug.png](http://upload-images.jianshu.io/upload_images/166866-817ef05ac5524819.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

因为图中的bug忙了大半天了😂.
 
#探索
android 3.0以后引进了属性动画.   
一般情况:Tween 动画我们使用ObjectAnimator,自定义动画我们使用ValueAnimator来实现.   
eg:
~~~
ObjectAnimator animX = ObjectAnimator.ofFloat(myView, "x", 50f);    
ObjectAnimator animY = ObjectAnimator.ofFloat(myView, "y", 100f);   
 AnimatorSet animSetXY = new AnimatorSet(); 
animSetXY.playTogether(animX, animY);   
 animSetXY.start();
~~~
这种方式也有几个缺点:   
1. propertyName 是 String 类型.String 类型可能导致漏写,多写,写错的风险.  
2. 当多个动画组合的时候代码变成很长很长.   


这个时候我们可以使用`View.animate()` 来组合这些动画。   
eg:
~~~
myView.animate().x(50f).y(100f);
~~~
看是很好的解决了这些问题,但是在实际的开发过程中也会发现一些缺点   
1.它的组合的只能是同一个view.   
2.它的方法只接受动画结束的状态，不接受开始和中间的状态.    
3.它的Listener 是绑定在View上，而不是Animator上面。所以说当你作用在一个 view上面有两个动画的时候,每个动画进行都会进入都同一个 Listener 中.   
总体来说这个方法不够灵活.



#使用AnimatorCompat创建动画    
我们可以使用AnimatorCompat来快速动画的创建.    
###gradle
/build.gradle
~~~
jitpack.io

repositories {
    maven {
        url "https://jitpack.io"
    }
}
~~~
/app/build.gradle
~~~
compile 'com.github.zzz40500:AnimatorCompat:0.1'
~~~
###usage
eg:
~~~
//不同 ObjectAnimator 动画的组合
AnimatorCompat.animate(mTv).rotationX(0, 360).translationY(0, 600).alpha(1, 0, 1).setDuration(3000).setInterpolator(new DecelerateInterpolator())
        .together() 
        .animate(mTv).rotationY(0, 360).setDuration(1000)
        .sequentially() 
        .animate(mTv).translationY(600, 0).setDuration(1000).together().animate(mTv).rotationX(360,0).setDuration(2000)
        .start(); 
~~~

eg 
~~~
// ObjectAnimator 动画和 ValueAnimator 动画的组合
AnimatorStream.Stream stream = AnimatorCompat.animate(mTv).translationX(0, 400).setDuration(700).setInterpolator(new DecelerateInterpolator());
AnimatorStream.Stream stream2 = AnimatorCompat.animate(mTv).translationY(0, 800).setDuration(700).setInterpolator(new DecelerateInterpolator()).addListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        Log.e(TAG, "onAnimationStart");
    }
}).withStartAction(new Runnable() {
    @Override
    public void run() {
        Log.e(TAG, "withStartAction");
    }
});
stream2.together(stream)
        .sequentially().ofFloat(400, 0).setDuration(700).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {

        ViewCompat.setTranslationX(mTv, (Float) valueAnimator.getAnimatedValue());

    }
}).together().ofFloat(800, 0).setDuration(1200).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ViewCompat.setTranslationY(mTv, (Float) valueAnimator.getAnimatedValue());

    }
}).setDuration(3000).start();

~~~
`sequentially()`: 后面的动画会在前一个动画执行完成后启动.   
`together`:后面的动画跟前一个动画一起执行.   

它的优点:   
1 它支持链式调用.   
2.它能组合ValueAnimator 和ObjectAnimator 的动画.   
3.它支持在作用在同一个 View 上面的各个动画单独设置配置(eg:duration,interpolator,addListener).   
4.它的 Listener 是绑定在Animator.   
5.它支持设置动画始末,中间态.   
###NOTE:   
 只支持 api14 及以上.   
当你有两个动画需要同时启动,同时又是作用在同一个 View 上面的.那么你最后是这样写:  
~~~
AnimatorCompat.animate(mTv).rotationX(0, 360).translationY(0, 600).setDuration(1000).start();
~~~
不要:
~~~
AnimatorCompat.animate(mTv).setDuration(1000).rotationX(0, 360).translationY(0, 600).setDuration(1000).start();
~~~

因为第一种情况会使用PropertyValuesHolder 把它组合成一个ObjectAnimator,而第2种情况会是两个ObjectAnimator 组成的 AnimatorSet .
效果是一样的,但是性能上面有区别.


#尾巴:  
这是对现有解决方案的不满,对更好解决方案的探索.  


相关链接:
>http://android-developers.blogspot.com/2011/05/introducing-viewpropertyanimator.html
