package com.iibcsad.recursion.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

public class EuclideanActivity extends AppCompatActivity {
    private ArrayList<String> gcds, lcds;
    double gcd, lcd;
    TextView lcm;
    private EditText inputIndex, inputIndexTwo;
    private Button calculate;

    private RecyclerView gcdView, lcdView;
    private RecyclerView.Adapter gcdAdapter, lcdAdapter;
    private RecyclerView.LayoutManager gcdLayoutManager, lcdLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euclidean);

        inputIndex = findViewById(R.id.a_euclidean_number_of_terms);
        inputIndexTwo = findViewById(R.id.a_euclidean_number_of_terms_two);
        lcm = findViewById(R.id.a_euclidean_lcd);
        gcds = new ArrayList<>();
        lcds = new ArrayList<>();

        calculate = findViewById(R.id.a_euclidean_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputIndex.getText().toString().matches("") || inputIndexTwo.getText().toString().matches(""))
                {
                    Snackbar.make(v, "Input something!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                gcds.clear();
                lcds.clear();
                calculateUntilIndex(Double.parseDouble(inputIndex.getText().toString()), Double.parseDouble(inputIndexTwo.getText().toString()));
            }
        });
    }

    private void calculateUntilIndex(double m, double n)
    {
        boolean flag = false;
        NumberFormat nf = new DecimalFormat("#0");
        double q;
        double r;
        double placeHolder;
        if(n > m)
        {
            placeHolder = m;
            m = n;
            n = placeHolder;
            inputIndex.setText(String.valueOf(nf.format(m)));
            inputIndexTwo.setText(String.valueOf(nf.format(n)));
        }

        while(!flag)
        {
            q = m/n;
            r = m%n;
            gcds.add(m + " = " + n + " x " + (int) q + " + " + nf.format(r));
            m = n;
            n = r;

            if(n <= 0)
            {
                gcd = m;
                gcds.add("gcd(" + inputIndex.getText().toString() + ", " + inputIndexTwo.getText().toString() + ") = " + nf.format(gcd));

                lcd = Double.parseDouble(inputIndex.getText().toString())* Double.parseDouble(inputIndexTwo.getText().toString());
                lcd /= gcd;
                lcm.setText("lcm(m, n) = " + inputIndex.getText().toString() + " * " + inputIndexTwo.getText().toString() + " / " + nf.format(gcd) + " = " + nf.format(lcd));

                break;
            }

        }


        initRecyclerView();
    }

    private void initRecyclerView()
    {
        gcdView = findViewById(R.id.a_euclidean_view_gcd);
        //lcdView = findViewById(R.id.a_euclidean_view_lcm);
        gcdView.setHasFixedSize(true);
        //lcdView.setHasFixedSize(true);
        gcdLayoutManager = new LinearLayoutManager(getApplicationContext());
        //lcdLayoutManager = new LinearLayoutManager(getApplicationContext());
        gcdAdapter = new SequenceAdapter(gcds);
        //lcdAdapter = new SequenceAdapter(lcds);

        gcdView.setLayoutManager(gcdLayoutManager);
        //lcdView.setLayoutManager(lcdLayoutManager);
        gcdView.setAdapter(gcdAdapter);
        //lcdView.setAdapter(lcdAdapter);
    }
}
