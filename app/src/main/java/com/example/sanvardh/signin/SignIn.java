package com.example.sanvardh.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sanvardh.MainActivity;
import com.example.sanvardh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private EditText mNumber_SignIn , password_SignIn;
    private String mNumber , password;
    private Button signinBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        mNumber_SignIn = findViewById(R.id.signInNumber);
        password_SignIn = findViewById(R.id.signInPassword);
        signinBtn = findViewById(R.id.signInBtn);

        autoLogIn();
        createButton();
    }

    private void autoLogIn() {

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private void createButton() {

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNumber = mNumber_SignIn.getText().toString().trim();
                password = password_SignIn.getText().toString().trim();

            }
        });
    }
}