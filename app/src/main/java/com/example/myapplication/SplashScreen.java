package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashScreen extends AppCompatActivity {

    private static int progress = 0;
    private int status = 0;
    ProgressBar progressBar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        }

        setContentView(R.layout.splashscreen);

        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 100){
                    status = loading();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(status);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent pindah = new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(pindah);
                        finish();
                    }
                });
            }
            public int loading(){
                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException ie){
                    ie.printStackTrace();
                }
                return ++progress;
            }
        }).start();

    }}

