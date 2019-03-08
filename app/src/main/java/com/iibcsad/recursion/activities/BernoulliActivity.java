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

public class BernoulliActivity extends AppCompatActivity {
    private ArrayList<String> numbers;
    private EditText inputIndex;
    private Button calculate;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private int num, denom;
    private final static double epsilon = 1E-10;

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
                else if(Integer.parseInt(inputIndex.getText().toString()) == 0)
                {
                    Snackbar.make(v, "Input is invalid!", Snackbar.LENGTH_SHORT).show();
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
        if(index == 1)
        {
            initRecyclerView();
            return;
        }
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

            NumberFormat nf = new DecimalFormat("#0");

            /*if(end == 0)
            {
                numbers.add(String.valueOf(end));
                continue;
            }
            else if(isInteger(end))
            {
                numbers.add(String.valueOf(end));
                //numbers.add(convertDecimalToFraction(end));
                //numbers.add("EDIT");
                continue;
            }*/

            //numbers.add(convertDecimalToFraction(end));
            numbers.add(String.valueOf(end));
            //numbers.add(convertDecimalToFraction(end));
            //numbers.add(fracion(end));
            //numbers.add("EDIT");
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

    private static String convertDecimalToFraction(double x){
        if (x < 0){
            return "-" + convertDecimalToFraction(-x);
        }
        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        NumberFormat nf = new DecimalFormat("#0");

        return nf.format(h1)+"/"+nf.format(k1);
    }

    public static boolean isInteger(final double d) {
        return Math.rint(d) == d;
    }

    public static String fracion(double x) {
        String a = "" + x;
        String spilts[] = a.split("\\."); // split using decimal
        int b = spilts[1].length(); // find the decimal length
        int denominator = (int) Math.pow(10, b); // calculate the denominator
        int numerator = (int) (x * denominator); // calculate the nerumrator Ex
        // 1.2*10 = 12
        int gcd = getGCD(numerator, denominator); // Find the greatest common
        // divisor bw them
        String fraction = "" + numerator / gcd + "/" + denominator / gcd;
        return fraction;
    }

    public static int getGCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return getGCD(n2, n1 % n2);
    }
}
