package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    String tag = "Assignment 2";
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num0;
    Button managerBut;

    Button cBut;
    Button buyBut;

    TextView productTypeText;
    TextView totalText;
    TextView quantityText;

    ListView productlist;

    AlertDialog.Builder builder;

    CashRegisterAdapter adapter;

    private CashRegister myCashRegister;
    //This will keep track of the selected item
    int item = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        managerBut =  findViewById(R.id.managerbut);
        num1 = findViewById(R.id.one);
        num2 =  findViewById(R.id.two);
        num3 =  findViewById(R.id.three);
        num4 =  findViewById(R.id.four);
        num5 =  findViewById(R.id.five);
        num6 =  findViewById(R.id.six);
        num7 =  findViewById(R.id.seven);
        num8 =  findViewById(R.id.eight);
        num9 =  findViewById(R.id.nine);
        num0 =  findViewById(R.id.zero);


        cBut =  findViewById(R.id.cOp);
        buyBut =  findViewById(R.id.buy);

        productlist =  findViewById(R.id.list);


        productTypeText =  findViewById(R.id.productTypeText);
        totalText =  findViewById(R.id.totalText);
        quantityText = findViewById(R.id.quantityText);

        builder = new AlertDialog.Builder(this);


        myCashRegister = new CashRegister();

        if (savedInstanceState != null)
            myCashRegister.listOfProducts = savedInstanceState.getParcelableArrayList("listofproducts");


        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);


        cBut.setOnClickListener(view -> clear());


        managerBut.setOnClickListener(view -> {
            Intent myIntent = new Intent(this,Manager_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("history", myCashRegister.HistoryList);
            myIntent.putExtra("bundle", bundle);
            startActivity(myIntent);
        });

        buyBut.setOnClickListener(view -> {

            if(myCashRegister.quantityWanted.isEmpty() || Integer.parseInt(myCashRegister.getQuantityWanted()) <= 0 || item < 0){
                Toast.makeText(this,"All fields must be entered!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(Integer.parseInt(myCashRegister.getQuantityWanted()) > Integer.parseInt(myCashRegister.getQuantity(item))){
                Toast.makeText(this,"Not enough quantity in stock!",Toast.LENGTH_SHORT).show();
                clear();
                return;
            }

            int quantity = Integer.parseInt(myCashRegister.getQuantityWanted());
            myCashRegister.setQuantity(item, quantity);

            myCashRegister.addHistory(myCashRegister.getName(item),totalText.getText().toString(), String.valueOf(quantity));

            adapter = new CashRegisterAdapter(this, myCashRegister.listOfProducts);

            productlist.setAdapter(adapter);

            builder.create();
            builder.setTitle("Thank you for your purchase!");
            builder.setMessage("Your purchase was " + myCashRegister.getQuantityWanted() +" " +myCashRegister.getName(item) + " for " + totalText.getText().toString() );
            builder.show();

           clear();
        });


        adapter = new CashRegisterAdapter(this, myCashRegister.listOfProducts);
        productlist.setAdapter(adapter);

        productlist.setOnItemClickListener((adapterView, view, i, l) -> {

            productTypeText.setText(myCashRegister.getName(i));
            item = i;
            if(quantityText.getText().toString().isEmpty()){

                return;
            }

          Double total = Double.parseDouble(myCashRegister.getPrice(i) ) * Double.parseDouble(quantityText.getText().toString());
            totalText.setText(String.format("%1$,.2f", total));

        });



    }

    @Override
    public void onClick(View view) {
        pushValue(view);
    }

    void pushValue(View view) {
        myCashRegister.push(((Button) view).getText().toString());

        quantityText.setText(myCashRegister.getQuantityWanted());

        if(item >= 0){
            Double total = Double.parseDouble(myCashRegister.getPrice(item) ) * Double.parseDouble(quantityText.getText().toString());
            totalText.setText(String.format("%1$,.2f", total));

        }

    }

public void clear(){
    myCashRegister.quantityWanted.clear();
    quantityText.setText("");
    totalText.setText("");
    productTypeText.setText("");

}

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"in onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myCashRegister = new CashRegister();
        Log.d(tag,"in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"in onDestroy");
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("listofproducts", myCashRegister.listOfProducts);
    }

}