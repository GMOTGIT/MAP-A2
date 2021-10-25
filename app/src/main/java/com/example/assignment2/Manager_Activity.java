package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Manager_Activity extends AppCompatActivity {

    Button historyBut;
    private CashRegister myCashRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        historyBut = findViewById(R.id.historybut);
        myCashRegister = new CashRegister();

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCashRegister.HistoryList = bundleFromMainActivity.getParcelableArrayList("history");

        }



        historyBut.setOnClickListener(view -> {

                if(myCashRegister.HistoryList.isEmpty()){
                    Toast.makeText(getApplicationContext(),"History is Empty", Toast.LENGTH_LONG).show();
                    return;
                }


            Intent myIntent = new Intent(this,History_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("history", myCashRegister.HistoryList);
            myIntent.putExtra("bundle", bundle);
            startActivity(myIntent);
        });







    }
}