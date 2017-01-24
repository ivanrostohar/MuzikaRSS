package com.example.ivan.muzikarss.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.muzikarss.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int TIME_OUT = 5000;

    private TextView txt_title, txt_copyright;
    private ImageView img_logo;
    private String title, extension, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_copyright = (TextView)findViewById(R.id.txt_copyright);
        img_logo = (ImageView)findViewById(R.id.img_logo);

        //setting up the animation of a logo
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(5000);

        img_logo.startAnimation(rotateAnimation);


        //setting the .HR of a given string to color red
        Spannable test = new SpannableString(getResources().getString(R.string.splash_screen_title));
        test.setSpan(new ForegroundColorSpan(Color.RED),6,9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_title.setText(test);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                finish();

            }
        }, TIME_OUT);
    }
}
