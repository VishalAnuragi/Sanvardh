package com.example.sanvardh.modelsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanvardh.R;

import com.example.sanvardh.modelsRecycler.modelsAdapter;
import com.example.sanvardh.modelsRecycler.modelsModel;

public class Models_Activity extends AppCompatActivity {

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

        modelsModel innermodel1[] = {

                new modelsModel("Physics", R.drawable.physics),
                new modelsModel("Biology", R.drawable.biology),
                new modelsModel("Mechanics", R.drawable.mechanics),
                new modelsModel("Astronomy", R.drawable.astronomy),
                new modelsModel("Architecture", R.drawable.architecture),
                new modelsModel("Chemistry", R.drawable.chemistry),
                new modelsModel("Physics", R.drawable.physics),
                new modelsModel("Biology", R.drawable.biology),
                new modelsModel("Mechanics", R.drawable.mechanics),
                new modelsModel("Astronomy", R.drawable.astronomy),
                new modelsModel("Architecture", R.drawable.architecture),
                new modelsModel("Chemistry", R.drawable.chemistry),

        };

        innerRecycler = findViewById(R.id.innerRecycler);
        innerRecycler.setLayoutManager(new GridLayoutManager( getApplicationContext(), 2 ));
        innerRecycler.setHasFixedSize(true);


        modelsAdapter inneradapter = new modelsAdapter(getApplicationContext() , innermodel1);
        innerRecycler.setAdapter(inneradapter);
        innerRecycler.setItemAnimator(new DefaultItemAnimator());

    }
}