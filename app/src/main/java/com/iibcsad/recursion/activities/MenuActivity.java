package com.iibcsad.recursion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iibcsad.recursion.DevelopersActivity;
import com.iibcsad.recursion.R;

public class MenuActivity extends AppCompatActivity {
    Button showDeveloperBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        showDeveloperBtn = findViewById(R.id.a_main_team_button);
        showDeveloperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDevelopers(v);
            }
        });

    }

    public void showDevelopers(View view)
    {
        Intent intent = new Intent(MenuActivity.this, DevelopersActivity.class);
        startActivity(intent);
    }

    public void showAbout(View view)
    {
        Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void showFibonacci(View view)
    {
        Intent intent = new Intent(MenuActivity.this, FibonacciActivity.class);
        startActivity(intent);
    }

    public void showLucas(View view)
    {
        Intent intent = new Intent(MenuActivity.this, LucasActivity.class);
        startActivity(intent);
    }

    public void showTribonacci(View view)
    {
        Intent intent = new Intent(MenuActivity.this, TribonacciActivity.class);
        startActivity(intent);
    }

    public void showCollatz(View view)
    {
        Intent intent = new Intent(MenuActivity.this, CollatzActivity.class);
        startActivity(intent);
    }

    public void showBernoulli(View view)
    {
        Intent intent = new Intent(MenuActivity.this, BernoulliActivity.class);
        startActivity(intent);
    }

    public void showEuclidean(View view)
    {
        Intent intent = new Intent(MenuActivity.this, EuclideanActivity.class);
        startActivity(intent);
    }

    public void showPrime(View view)
    {
        Intent intent = new Intent(MenuActivity.this, PrimeActivity.class);
        startActivity(intent);
    }

    public void showFermat(View view)
    {
        Intent intent = new Intent(MenuActivity.this, FermatActivity.class);
        startActivity(intent);
    }
}
