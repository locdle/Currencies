package com.locdle.currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Button mCalcuationButton;
    private TextView mConverterTextView;
    private EditText mAmountEditText;
    private Spinner mForSpinner, mHomeSpinner;
    private String[] mCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arrayList = (ArrayList<String>) getIntent().getSerializableExtra("key_arraylist");
        Collections.sort(arrayList);
        mCurrency = arrayList.toArray(new String [arrayList.size()]);

        mConverterTextView = (TextView) findViewById(R.id.txt_converted);
        mAmountEditText = (EditText) findViewById(R.id.edt_amount);
        mCalcuationButton = (Button) findViewById(R.id.btn_calc);
        mForSpinner = (Spinner) findViewById(R.id.spn_for);
        mHomeSpinner = (Spinner) findViewById(R.id.spn_hom);
    }
}
