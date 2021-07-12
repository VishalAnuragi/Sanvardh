package com.example.sanvardh.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sanvardh.MainActivity;
import com.example.sanvardh.R;
import com.example.sanvardh.signin.SignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_SCREEN = 3000;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user != null){
                    Intent dashboardIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(dashboardIntent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, SignIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_SCREEN);

    }


}