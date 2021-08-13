package com.bluesky.automationjiahua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bluesky.automationjiahua.base.App;
import com.bluesky.atuomationjiahua.databinding.ActivitySplashBinding;
import com.bluesky.automationjiahua.utils.AssetsCopyUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initData();
    }

    private void initView() {
        binding.btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
        String path = getDatabasePath(App.DATA_BASE_NAME).getAbsolutePath();
        AssetsCopyUtils.copyAssetsFile2Phone(this, App.DATA_BASE_NAME, path);
        binding.btnEnter.setVisibility(View.VISIBLE);
    }

}