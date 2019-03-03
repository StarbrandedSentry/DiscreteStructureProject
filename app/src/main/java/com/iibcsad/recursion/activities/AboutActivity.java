package com.iibcsad.recursion.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.iibcsad.recursion.R;

public class AboutActivity extends AppCompatActivity {
    TextView lee, serdon, dean, sir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /*dean = findViewById(R.id.a_about_dean);
        serdon = findViewById(R.id.a_about_serdon);
        lee = findViewById(R.id.a_about_lee);
        sir = findViewById(R.id.a_about_sir);

        dean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemeDialog();
            }
        });
        serdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemeDialog();
            }
        });
        lee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemeDialog();
            }
        });
        sir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfMemeDialog();
            }
        });*/
    }

    public void showMemeDialog(View view)
    {
        AlertDialog.Builder memer = new AlertDialog.Builder(this);
        memer.setMessage("Pogi to eh").setPositiveButton("Oo nga eh", memeClickListener);
        memer.show();
    }

    public void showProfMemeDialog(View view)
    {
        AlertDialog.Builder memer = new AlertDialog.Builder(this);
        memer.setMessage("Eto pinaka-malupit na prof").setPositiveButton("Agreed", memeClickListener);
        memer.show();
    }

    private DialogInterface.OnClickListener memeClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which)
            {
                case DialogInterface.BUTTON_POSITIVE:
                    break;

            }
        }
    };
}
