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

        inputIndex = findViewById(R.id.a_fermat_input);
        numbers = new ArrayList<>();

        calculate = findViewById(R.id.a_fermat_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputIndex.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Input something!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else if(isPrime(Double.parseDouble(inputIndex.getText().toString())))
                {
                    Snackbar.make(v, "Input number already a prime number!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                numbers.clear();
                calculateUntilIndex(Double.parseDouble(inputIndex.getText().toString()));
            }
        });
    }

    private void calculateUntilIndex(double num)
    {
        NumberFormat nb = new DecimalFormat("#0");
        boolean flag = false;
        Double N = num;
        double x, y;
        String xS, yS;
        //Toast.makeText(getApplicationContext(), isPrime(index) + "", Toast.LENGTH_SHORT).show();

        Double nN = Math.sqrt(N.intValue());
        int n = nN.intValue();
        if(n < Math.sqrt(N.intValue()))
        {
            n++;
        }
        N = Double.valueOf(n);

        while (!flag)
        {
            numbers.add(n + " - " + nb.format(num) + " = " + ((n*n)-num));

            if(Math.sqrt(((N * N) - num)) % 1 == 0)
            {
                //Toast.makeText(getApplicationContext(), " - " + n, Toast.LENGTH_SHORT).show();
                x = n  - Math.sqrt(((N * N) - num));
                y = n  + Math.sqrt(((N * N) - num));
                numbers.add("FACTORS:(" + nb.format(x) + "), (" + nb.format(y) + ")");
                break;
            }

            N++;
            n++;
        }


        initRecyclerView();
    }

    private void initRecyclerView()
    {
        recyclerView = findViewById(R.id.a_fermat_view);
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
