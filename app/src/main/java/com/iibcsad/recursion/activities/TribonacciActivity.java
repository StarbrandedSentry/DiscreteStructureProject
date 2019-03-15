package com.iibcsad.recursion.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iibcsad.recursion.R;
import com.iibcsad.recursion.adapters.SequenceAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class TribonacciActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex, simpleAnswer;
    private Button calculate;
    private TextView showRecycler;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribonacci);

        inputIndex = findViewById(R.id.a_tribonacci_number_of_terms);
        recyclerView = findViewById(R.id.a_tribonacci_view);
        simpleAnswer = findViewById(R.id.a_tribonacci_simple_view);
        showRecycler = findViewById(R.id.a_tribonacci_show_recycler);
        showRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.clear();
                if(recyclerView.getVisibility() == View.GONE)
                {
                    recyclerView.setVisibility(View.VISIBLE);
                    calculateUntilIndex(Double.parseDouble(inputIndex.getText().toString()));
                }
                else
                {
                    recyclerView.setVisibility(View.GONE);
                }

                calculateSimple(Double.parseDouble(inputIndex.getText().toString()));
                showRecycler.setVisibility(View.VISIBLE);
            }
        });
        numbers = new ArrayList<>();

        calculate = findViewById(R.id.a_tribonacci_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecycler.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                if(inputIndex.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Input something!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(Integer.parseInt(inputIndex.getText().toString()) <= 3)
                {
                    Snackbar.make(v, "Input is invalid!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                calculateSimple(Double.parseDouble(inputIndex.getText().toString()));
                showRecycler.setVisibility(View.VISIBLE);
            }
        });
    }

    private void calculateSimple(double index)
    {
        String series = "Tn = 0, 0, 1";
        NumberFormat nb = new DecimalFormat("#0");
        double a = 0, b = 0, c = 1, z, noDecimal;

        for(int i = 0; i < index - 3; i++)
        {
            z = a + b + c;
            series += ", " + nb.format(z);
            a = b;
            b = c;
            c = z;
        }
        series += " for 0 <= n <= " + nb.format(index-1);
        simpleAnswer.setText(series);
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
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerAdapter = new SequenceAdapter(numbers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
