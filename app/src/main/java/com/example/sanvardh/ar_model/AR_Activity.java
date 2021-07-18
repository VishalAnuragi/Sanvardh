package com.example.sanvardh.ar_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanvardh.R;
import com.example.sanvardh.innerRecycler.innerAdapter;
import com.example.sanvardh.innerRecycler.innerModel;
import com.example.sanvardh.topicsRecycler.topicAdapter;
import com.example.sanvardh.topicsRecycler.topicModel;

public class AR_Activity extends AppCompatActivity {

    private TextView title;
    private ImageView titleImg;
    private RecyclerView innerRecycler;

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

        innerModel innermodel1[] = {

                new innerModel("Physics", R.drawable.physics),
                new innerModel("Biology", R.drawable.biology),
                new innerModel("Mechanics", R.drawable.mechanics),
                new innerModel("Astronomy", R.drawable.astronomy),
                new innerModel("Architecture", R.drawable.architecture),
                new innerModel("Chemistry", R.drawable.chemistry),
                new innerModel("Physics", R.drawable.physics),
                new innerModel("Biology", R.drawable.biology),
                new innerModel("Mechanics", R.drawable.mechanics),
                new innerModel("Astronomy", R.drawable.astronomy),
                new innerModel("Architecture", R.drawable.architecture),
                new innerModel("Chemistry", R.drawable.chemistry),
                new innerModel("Physics", R.drawable.physics),
                new innerModel("Biology", R.drawable.biology),
                new innerModel("Mechanics", R.drawable.mechanics),
                new innerModel("Astronomy", R.drawable.astronomy),
                new innerModel("Architecture", R.drawable.architecture),
                new innerModel("Chemistry", R.drawable.chemistry)
        };

        innerRecycler = findViewById(R.id.innerRecycler);
        innerRecycler.setLayoutManager(new GridLayoutManager( getApplicationContext(), 2 ));
        innerRecycler.setHasFixedSize(true);


        innerAdapter inneradapter = new innerAdapter(getApplicationContext() , innermodel1);
        innerRecycler.setAdapter(inneradapter);
        innerRecycler.setItemAnimator(new DefaultItemAnimator());

    }
}