package com.example.sanvardh.modelsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanvardh.R;

import com.example.sanvardh.modelsRecycler.modelsAdapter;
import com.example.sanvardh.modelsRecycler.modelsModel;

public class Models_Activity extends AppCompatActivity {

    private TextView title;
    private ImageView titleImg;
    private RecyclerView innerRecycler;
    public String topic;
    public String topicShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        title = findViewById(R.id.titleTxt);
        titleImg = findViewById(R.id.titleImg);

        Bundle bundle = getIntent().getExtras();
         topic = bundle.getString("TOPIC");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TOPIC",topic);
        editor.apply();


        createTitles(topic);
    }

    private void createTitles(String topic) {
        title.setText(topic);
        switch(topic) {
            case "PHYSICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.physics));

                modelsModel innermodel1[] = {

                        new modelsModel("Cube", R.drawable.cube),
                        new modelsModel("Antique Camera", R.drawable.antiquecamera),
                        new modelsModel("Boom Box", R.drawable.boombox),
                        new modelsModel("SciFi Helmet", R.drawable.helmet)
                };

                createModelsRecycler(innermodel1);
                break;

            case "BIOLOGY" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.biology));

                modelsModel innermodel2[] = {

                        new modelsModel("Fish", R.drawable.fish),
                        new modelsModel("Fox", R.drawable.fox),
                        new modelsModel("Avacado", R.drawable.avacado)
                };

                createModelsRecycler(innermodel2);
                break;

            case "MECHANICS" :
                titleImg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.mechanics));

                modelsModel innermodel3[] = {

                        new modelsModel("2 Stroke Engine", R.drawable.twostrokeengine),
                        new modelsModel("Gear Box", R.drawable.gearbox),
                        new modelsModel("Reciprocating Saw", R.drawable.saw),
                        new modelsModel("Delivery Truck", R.drawable.truck),
                        new modelsModel("Robot", R.drawable.brainstem)
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

                        new modelsModel("Sponza", R.drawable.sponza),
                        new modelsModel("Virtual City", R.drawable.virtualcity)
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