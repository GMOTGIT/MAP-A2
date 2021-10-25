package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {
    private CashRegister myCashRegister;

    TextView historyNameText;
    TextView historyPriceText;
    TextView historyQuantityText;
    TextView historyDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        historyNameText = findViewById(R.id.historyNameText);
        historyPriceText = findViewById(R.id.historyPriceText);
        historyQuantityText = findViewById(R.id.historyQuantityText);
        historyDateText =  findViewById(R.id.historyDateText);

        myCashRegister = new CashRegister();



        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCashRegister.HistoryList = bundleFromMainActivity.getParcelableArrayList("history");

        }
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price= intent.getStringExtra("price");
        String quantity = intent.getStringExtra("quantity");;
        String date = intent.getStringExtra("date");


        historyNameText.setText(getString(R.string.productLine) + name);
        historyPriceText.setText(getString(R.string.priceLine) + price);
        historyQuantityText.setText(getString(R.string.quantityLine) + quantity);
        historyDateText.setText(getString(R.string.dateLine)  + date);



    }

//    public void onBackPressed(){
//        Intent myIntent = new Intent(this,History_Activity.class);
//        Bundle outState = new Bundle();
//        outState.putParcelableArrayList("history", myCashRegister.listOfProducts);
//        myIntent.putExtra("bundle", outState);
//        startActivity(myIntent);
//
//    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("listofproducts", myCashRegister.listOfProducts);
    }
}