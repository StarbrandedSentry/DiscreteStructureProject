package com.iibcsad.recursion.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.iibcsad.recursion.R;
import com.iibcsad.recursion.adapters.SequenceAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FermatActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex;
    private Button calculate;
    private String series = "", pure = "";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermat);
    }

    private void calculateUntilIndex(double index)
    {
        NumberFormat nb = new DecimalFormat("#0");
        series = "";
        pure = "";
        double end;
        int count = 0;
        boolean flag = false;
        //Toast.makeText(getApplicationContext(), isPrime(index) + "", Toast.LENGTH_SHORT).show();

        while (!flag)
        {
            if(isPrime(index))
            {
                /*pure = nb.format(index) + pure;
                numbers.add(pure);*/
                break;
            }
            else
            {
                series = nb.format(index / divisibleBy(index)) + pure+ " * " + nb.format(divisibleBy(index));
                pure +=  " * " + nb.format(divisibleBy(index));
                numbers.add(series);
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
