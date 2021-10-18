package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class History_Activity extends AppCompatActivity {
    private CashRegister myCashRegister;
    RecyclerView recyclerlist;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerlist = (RecyclerView) findViewById(R.id.recyclerlist);

        myCashRegister = new CashRegister();

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCashRegister.HistoryList = bundleFromMainActivity.getParcelableArrayList("history");

        }
        recyclerlist.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HistoryAdapter(myCashRegister.HistoryList, this, new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onHistoryClicked(Product item) {

                intent(item);
            }
        });

        recyclerlist.setAdapter(adapter);
    }


    public void intent(Product item){
        Intent myIntent = new Intent(this,HistoryDetailActivity.class);
        myIntent.putExtra("name", item.name);
        myIntent.putExtra("price", item.price);
        myIntent.putExtra("quantity", item.quantity);
        myIntent.putExtra("date", item.date);
        startActivity(myIntent);


    }


}