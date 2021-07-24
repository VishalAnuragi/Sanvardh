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
    }

    private void createTitles(String topic) {
        title.setText(topic);
        switch(topic) {
            case "PHYSICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.physics));

                modelsModel innermodel1[] = {

                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics),
                        new modelsModel("Physics", R.drawable.physics)
                };

                createModelsRecycler(innermodel1);
                break;

            case "BIOLOGY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.biology));

                modelsModel innermodel2[] = {

                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology),
                        new modelsModel("Biology", R.drawable.biology)
                };

                createModelsRecycler(innermodel2);
                break;

            case "MECHANICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mechanics));

                modelsModel innermodel3[] = {

                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics),
                        new modelsModel("Mechanics", R.drawable.mechanics)
                };

                createModelsRecycler(innermodel3);
                break;

            case "ASTRONOMY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.astronomy));

                modelsModel innermodel4[] = {

                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy),
                        new modelsModel("Astronomy", R.drawable.astronomy)
                };

                createModelsRecycler(innermodel4);
                break;

            case "ARCHITECTURE" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.architecture));

                modelsModel innermodel5[] = {

                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture),
                        new modelsModel("Architecture", R.drawable.architecture)
                };

                createModelsRecycler(innermodel5);
                break;

            case "CHEMISTRY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chemistry));

                modelsModel innermodel6[] = {

                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry),
                        new modelsModel("Chemistry", R.drawable.chemistry)
                };

                createModelsRecycler(innermodel6);
                break;
        }
    }

    private void createModelsRecycler(modelsModel[] innermodel) {

        innerRecycler = findViewById(R.id.innerRecycler);
        innerRecycler.setLayoutManager(new GridLayoutManager( getApplicationContext(), 2 ));
        innerRecycler.setHasFixedSize(true);

        modelsAdapter inneradapter = new modelsAdapter(getApplicationContext() , innermodel);
        innerRecycler.setAdapter(inneradapter);
        innerRecycler.setItemAnimator(new DefaultItemAnimator());

    }
}