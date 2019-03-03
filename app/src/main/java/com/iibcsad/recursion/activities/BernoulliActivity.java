package com.iibcsad.recursion.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iibcsad.recursion.R;
import com.iibcsad.recursion.adapters.SequenceAdapter;

import java.util.ArrayList;

public class BernoulliActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex;
    private Button calculate;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bernoulli);
        inputIndex = findViewById(R.id.a_bernoulli_number_of_terms);
        numbers = new ArrayList<>();

        calculate = findViewById(R.id.a_bernoulli_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputIndex.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Input something!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                numbers.clear();
                calculateUntilIndex(Double.parseDouble(inputIndex.getText().toString()));
            }
        });
    }

    private void calculateUntilIndex(double index)
    {
        numbers.add(String.valueOf(1));
        Double number = index;

        double end, middle;
        double[][] b = new double[number.intValue()][number.intValue()];

        for(int i = 0; i < index; i++)
        {
            middle = 1;
            end = -1;
            for(int j = 0; j <= i; j++)
            {
                if(j == 0)
                {
                    b[i][j] = 1;
                    //middle = 1;
                    continue;
                }

                if(j == i)
                {
                    b[i][j] = b[i-1][j-1] + 1;
                    end = (middle * -1) / b[i][j];
                    if(end == 0)
                    {
                        end = end * -1;
                    }
                    continue;
                }

                b[i][j] = b[i-1][j-1] + b[i-1][j];
                middle += b[i][j] * Double.parseDouble(numbers.get(j));
            }
            if(i == 0)
            {
                //numbers.add(String.valueOf(end * -1));
                continue;
            }

            numbers.add(String.valueOf(end));
        }

        initRecyclerView();
    }

    private void initRecyclerView()
    {
        recyclerView = findViewById(R.id.a_bernoulli_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerAdapter = new SequenceAdapter(numbers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
