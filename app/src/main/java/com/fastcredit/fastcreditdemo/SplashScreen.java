package com.fastcredit.fastcreditdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    }

    private void startanim() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Animation animationtwo = AnimationUtils.loadAnimation(this, R.anim.fadeinfast);
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
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
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