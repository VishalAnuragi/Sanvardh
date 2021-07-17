package com.example.sanvardh.ar_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanvardh.R;

public class AR_Activity extends AppCompatActivity {

    private TextView title;
    private ImageView titleImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        title = findViewById(R.id.titleTxt);
        titleImg = findViewById(R.id.titleImg);

        Bundle bundle = getIntent().getExtras();
        String topic = bundle.getString("TOPIC");



        createTitles(topic);
        createMainRecycler();
    }

    private void createTitles(String topic) {
        title.setText(topic);
        switch(topic) {
            case "PHYSICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.physics));
                break;

            case "BIOLOGY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.biology));
                break;

            case "MECHANICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mechanics));
                break;

            case "ASTRONOMY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.astronomy));
                break;

            case "ARCHITECTURE" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.architecture));
                break;

            case "CHEMISTRY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chemistry));
                break;
        }
    }

    private void createMainRecycler() {

    }
}