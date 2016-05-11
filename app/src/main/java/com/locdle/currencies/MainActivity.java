package com.locdle.currencies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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

        ArrayList<String> arrayList = (ArrayList<String>) getIntent().getSerializableExtra(SplashActivity.KEY_ARRAYLIST);
        Collections.sort(arrayList);
        mCurrency = arrayList.toArray(new String [arrayList.size()]);

        mConverterTextView = (TextView) findViewById(R.id.txt_converted);
        mAmountEditText = (EditText) findViewById(R.id.edt_amount);
        mCalcuationButton = (Button) findViewById(R.id.btn_calc);
        mForSpinner = (Spinner) findViewById(R.id.spn_for);
        mHomeSpinner = (Spinner) findViewById(R.id.spn_hom);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_closed, mCurrency);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mHomeSpinner.setAdapter(arrayAdapter);
        mForSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_invert:
                //TODO define behavior here
                invertCurrencies();
                break;
            case R.id.mnu_codes:
                //TODO define behavior here
                launchBrowser(SplashActivity.URL_CODES);
                break;
            case R.id.mnu_exit:
                finish();
                break;
        }
        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    private void launchBrowser(String strUri) {
        if (isOnline()) {
            Uri uri = Uri.parse(strUri);
            //call an implicit intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
    private void invertCurrencies() {
        int nFor = mForSpinner.getSelectedItemPosition();
        int nHom = mHomeSpinner.getSelectedItemPosition();
        mForSpinner.setSelection(nHom);
        mHomeSpinner.setSelection(nFor);
        mConverterTextView.setText("");
    }
}
