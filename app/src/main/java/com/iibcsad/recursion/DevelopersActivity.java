package com.iibcsad.recursion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iibcsad.recursion.adapters.DeveloperInfoAdapter;

import java.util.ArrayList;

public class DevelopersActivity extends AppCompatActivity {
    private ArrayList<String> name;
    private ArrayList<String> age;
    private ArrayList<String> image;
    private int[] images;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        setUpArrays();
        initDevelopers();
    }

    private void initDevelopers()
    {
        recyclerView = findViewById(R.id.a_developers_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAdapter = new DeveloperInfoAdapter(name, age, image);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void setUpArrays()
    {
        name = new ArrayList<>();
        age = new ArrayList<>();
        image = new ArrayList<>();

        name.add("Dean Villamia");
        image.add(String.valueOf(R.drawable.villamia));
        age.add("29");
        name.add("Reyster Serdoncillo");
        image.add(String.valueOf(R.drawable.serdoncillo));
        age.add("19");
        name.add("Nur-in Chua");
        image.add(String.valueOf(R.drawable.chuaelfel));
        age.add("20");
        name.add("Elfel Opleda");
        image.add(String.valueOf(R.drawable.chuaelfel));
        age.add("20");
        name.add("Ronn Mico Lee");
        image.add(String.valueOf(R.drawable.lee));
        age.add("16");
    }
}
