package com.example.sanvardh.ar_model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sanvardh.R;

public class AR_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        Bundle bundle = getIntent().getExtras();
        String topic = bundle.getString("TOPIC");


        createMainRecycler();
    }

    private void createMainRecycler() {

    }
}