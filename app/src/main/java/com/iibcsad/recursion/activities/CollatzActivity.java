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

public class CollatzActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex;
    private Button calculate;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collatz);

        inputIndex = findViewById(R.id.a_collatz_number_of_terms);
        numbers = new ArrayList<>();

        calculate = findViewById(R.id.a_collatz_calculate);
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
        NumberFormat nb = new DecimalFormat("#0");
        numbers.add(String.valueOf(nb.format(index)));

        //Toast.makeText(getApplicationContext(), String.valueOf(isEven(index)), Toast.LENGTH_SHORT).show();

        while(index > 1)
        {
            if(isEven(index))
            {
                index = index / 2;

            }
            else
            {
                index = index * 3;
                index = index + 1;
            }
            numbers.add(String.valueOf(nb.format(index)));
        }

        initRecyclerView();
    }

    private boolean isEven(double num)
    {
        if(num % 2 == 0)
        {
            return true;
        }

        return false;
    }

    private void initRecyclerView()
    {
        recyclerView = findViewById(R.id.a_collatz_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerAdapter = new SequenceAdapter(numbers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
