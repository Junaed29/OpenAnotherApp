package com.jpsoft.openanotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.jpsoft.openanotherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        valueAnimator = ValueAnimator.ofFloat(50f, 00f);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();

                binding.imageViewIcon.setAlpha(1 - value / 50);
                binding.imageViewIcon.setTranslationY(-value);

                binding.textViewAppName.setAlpha(1 - value / 50);
                binding.textViewAppName.setTranslationY(value);


            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.setDuration(1500L);

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Open another activity
                Intent intent = new Intent(MainActivity.this, HandleAnotherAppActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.start();
    }
}