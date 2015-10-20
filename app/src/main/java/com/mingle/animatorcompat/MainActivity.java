package com.mingle.animatorcompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.mingle.anim.AnimatorCompat;
import com.mingle.anim.AnimatorStream;
import com.mingle.animatorcompat.demo.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick1(View view) {

        AnimatorCompat.animate(mTv).translationX(0, 200).translationY(0, 600).alpha(1, 0, 1).setDuration(1000).setInterpolator(new DecelerateInterpolator()).sequentially()

                .animate(mTv).translationX(200, 0).setDuration(1000).sequentially()

                .animate(mTv).translationY(600, 0).setDuration(2000)
                .start();

    }

    public void onClick2(View view) {

        AnimatorCompat
                .animate(mTv).rotationX(0, 360).translationY(0, 600).alpha(1, 0, 1).setDuration(3000).setInterpolator(new DecelerateInterpolator())
                .together()

                .animate(mTv).rotationY(0, 360).setDuration(1000)
                .sequentially()
                .animate(mTv).translationY(600, 0).setDuration(1000).together().animate(mTv).rotationX(360,0).setDuration(2000)
                .start();

    }

    public void onClick3(View view) {

        AnimatorStream.Stream stream= AnimatorCompat.animate(mTv).translationX(0,400).setDuration(700).setInterpolator(new DecelerateInterpolator());
        AnimatorStream.Stream stream2=  AnimatorCompat.animate(mTv).translationY(0, 800).setDuration(700).setInterpolator(new DecelerateInterpolator()).addListener(new AnimatorListenerAdapter() {
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


    }
}
