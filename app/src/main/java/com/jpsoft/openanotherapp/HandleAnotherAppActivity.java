package com.jpsoft.openanotherapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jpsoft.openanotherapp.databinding.ActivityHandleAnotherAppBinding;

public class HandleAnotherAppActivity extends AppCompatActivity {

    final String HURRAY_HEALTH_PACKAGE_NAME= "com.hoorayhealthcare.hoorayhealthpro";
    ActivityHandleAnotherAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandleAnotherAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOpenAnotherApp.setOnClickListener(v -> {
            startNewActivity(this,HURRAY_HEALTH_PACKAGE_NAME);
        });
    }

    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(HandleAnotherAppActivity.this, "App not installed in your device", Toast.LENGTH_SHORT).show();
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }
}