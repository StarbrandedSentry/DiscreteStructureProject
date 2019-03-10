package com.iibcsad.recursion.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iibcsad.recursion.R;
import com.iibcsad.recursion.adapters.SequenceAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class PrimeActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private ArrayList<Double> prep;
    private EditText inputIndex;
    private Button calculate;
    private String series = "", pure = "";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        inputIndex = findViewById(R.id.a_prime_number_of_terms);
        numbers = new ArrayList<>();
        prep = new ArrayList<>();

        calculate = findViewById(R.id.a_prime_calculator);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputIndex.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Input something!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(inputIndex.getText().toString().equals("1"))
                {
                    Snackbar.make(v, "One is already a perfect square!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(isPrime(Double.parseDouble(inputIndex.getText().toString())))
                {
                    Snackbar.make(v, "Input number already a prime number!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                numbers.clear();
                prep.clear();
                calculateUntilIndex(Double.parseDouble(inputIndex.getText().toString()));
            }
        });
    }

    private void calculateUntilIndex(double index) {
        NumberFormat nb = new DecimalFormat("#0");
        series = "";
        pure = "";
        double end;
        int count = 0;
        boolean flag = false;
        //Toast.makeText(getApplicationContext(), isPrime(index) + "", Toast.LENGTH_SHORT).show();

        while (!flag) {
            if (isPrime(index)) {
                /*pure = nb.format(index) + pure;
                numbers.add(pure);*/
                prep.add(divisibleBy(index));
                Collections.sort(prep);
                //numbers.add(series);
                String thisSeries = "";
                for (int i = 0; i < prep.size(); i++)
                {
                    if(i == prep.size() - 1)
                    {
                        thisSeries += nb.format(prep.get(i));
                        continue;
                    }
                    thisSeries += nb.format(prep.get(i)) + " * ";
                }
                numbers.add(thisSeries);

                break;
            } else {
                /*if(count == 0)
                {
                    series = nb.format(index / divisibleBy(index)) + " * " + nb.format(divisibleBy(index));
                    pure +=  " * " + nb.format(divisibleBy(index));
                    numbers.add(series);
                    index = index / divisibleBy(index);
                    count = 1;
                    continue;
                }*/
                series = nb.format(index / divisibleBy(index)) + pure + " * " + nb.format(divisibleBy(index));
                pure += " * " + nb.format(divisibleBy(index));
                prep.add(divisibleBy(index));
                Collections.sort(prep);
                //numbers.add(series);
                String thisSeries = "";
                for (int i = 0; i < prep.size(); i++)
                {
                    if(i == prep.size() - 1)
                    {
                        thisSeries += nb.format(prep.get(i));
                        continue;
                    }
                    thisSeries += nb.format(prep.get(i)) + " * ";
                }
                numbers.add(nb.format(index / divisibleBy(index)) + " * " + thisSeries);

                index = index / divisibleBy(index);
            }
        }


        initRecyclerView();
    }

    private void initRecyclerView()
    {
        recyclerView = findViewById(R.id.a_prime_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAdapter = new SequenceAdapter(numbers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private double divisibleBy(double number)
    {
        for(int i = 2; i <= number; i++)
        {
            if(number % i == 0)
            {
                return i;
            }
        }
        return 1;
    }

    private boolean isPrime(double number)
    {
        for(int i = 2; i <= number; i++)
        {
            if((number % i == 0 && i != number) || number == 1)
            {
                return false;
            }
        }

        return true;
    }
}
