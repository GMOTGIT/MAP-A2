package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        historyNameText = (TextView) findViewById(R.id.historyNameText);
        historyPriceText = (TextView) findViewById(R.id.historyPriceText);
        historyQuantityText = (TextView) findViewById(R.id.historyQuantityText);
        historyDateText = (TextView) findViewById(R.id.historyDateText);

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


        historyNameText.setText("Product: " + name);
        historyPriceText.setText("Price: " + price);
        historyQuantityText.setText("Quantity: " + quantity);
        historyDateText.setText("Purchase Date: "  + date);



    }
}