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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class TribonacciActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex;
    private Button calculate;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribonacci);

        inputIndex = findViewById(R.id.a_tribonacci_number_of_terms);
        numbers = new ArrayList<>();

        calculate = findViewById(R.id.a_tribonacci_calculate);
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
        numbers.add(String.valueOf(0));
        if(index == 1)
        {
            initRecyclerView();
            return;
        }
        numbers.add(String.valueOf(0));
        if(index == 2)
        {
            initRecyclerView();
            return;
        }
        numbers.add(String.valueOf(1));

        double a = 0, b = 0, c = 1, z, noDecimal;

        for(int i = 0; i < index - 3; i++)
        {
            z = a + b + c;
            NumberFormat nb = new DecimalFormat("#0");
            numbers.add(String.valueOf(nb.format(z)));
            a = b;
            b = c;
            c = z;
        }

        initRecyclerView();
    }

    private void initRecyclerView()
    {
        recyclerView = findViewById(R.id.a_fibonacci_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerAdapter = new SequenceAdapter(numbers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
