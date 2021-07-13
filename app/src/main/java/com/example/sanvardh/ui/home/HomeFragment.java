package com.example.sanvardh.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanvardh.R;
import com.example.sanvardh.topicsRecycler.topicAdapter;
import com.example.sanvardh.topicsRecycler.topicModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView topicRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        createRecycler(root);


        return root;
    }

    private void createRecycler(View root) {

        topicModel topicmodel[] = {

                new topicModel("Physics", R.drawable.physics),
                new topicModel("Biology", R.drawable.biology),
                new topicModel("Mechanics", R.drawable.mechanics),
                new topicModel("Astronomy", R.drawable.astronomy),
                new topicModel("Architecture", R.drawable.architecture),
                new topicModel("Chemistry", R.drawable.chemistry)
        };

        topicRecycler = root.findViewById(R.id.topicsRecyclerView);
        topicRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        topicRecycler.setHasFixedSize(true);


        topicAdapter topicadapter = new topicAdapter(getActivity() , topicmodel);
        topicRecycler.setAdapter(topicadapter);
        topicRecycler.setItemAnimator(new DefaultItemAnimator());


    }
}