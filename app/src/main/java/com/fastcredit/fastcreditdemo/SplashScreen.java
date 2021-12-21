package com.fastcredit.fastcreditdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView Text;
    ImageView Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Text = findViewById(R.id.txt);
        Image = findViewById(R.id.image);

        startanim();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        hideSystemUI();
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }

    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 200;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(Image.getVisibility() == View.VISIBLE){
                            Image.setVisibility(View.INVISIBLE);
                        }else{
                            Image.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }

    private void startanim() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Animation animationtwo = AnimationUtils.loadAnimation(this, R.anim.fadeinfast);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);

        Text.setVisibility(View.INVISIBLE);
        //txt!!.visibility = View.INVISIBLE;

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation h) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                new Handler().postDelayed({
//                                txt!!.visibility = View.VISIBLE
//                        txt!!.startAnimation(b)
//                }, 2000)

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Text.setVisibility(View.VISIBLE);
                        Text.startAnimation(animationtwo);
                        blink();
                        //Write whatever to want to do after delay specified (1 sec)
                        Log.d("Handler", "Running Handler");
                    }
                },2000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }




        });
        animationtwo.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation h) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, LoginPagee.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeinfast, R.anim.fadeoutfast);
                        finish();
                    }
                },4000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
//
//            Override onAnimationEnd(animation: Animation) {
//                Handler().postDelayed({
//                        startActivity(Intent(this@Splash, Welcome::class.java))
//                overridePendingTransition(R.anim.fadeinfast, R.anim.fadeoutfast)
//                finish()
//                //fetchUserState()
//                }, 4000)
//            }
//
//            Override onAnimationRepeat(animation: Animation) {
//
//            }
        });
        Image.startAnimation(animation);
        //image!!.startAnimation(a)

    }
}