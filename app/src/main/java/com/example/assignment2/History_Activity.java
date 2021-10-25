package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class History_Activity extends AppCompatActivity {
    private CashRegister myCashRegister;
    RecyclerView recyclerlist;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerlist = findViewById(R.id.recyclerlist);

        myCashRegister = new CashRegister();

        if (savedInstanceState != null)
            myCashRegister.listOfProducts = savedInstanceState.getParcelableArrayList("listofproducts");

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCashRegister.HistoryList = bundleFromMainActivity.getParcelableArrayList("history");

        }

        recyclerlist.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HistoryAdapter(myCashRegister.HistoryList, this, this::intent);

        recyclerlist.setAdapter(adapter);
    }


    public void intent(Product item){
        Intent myIntent = new Intent(this,HistoryDetailActivity.class);
        myIntent.putExtra("name", item.name);
        myIntent.putExtra("price", String.valueOf(item.price));
        myIntent.putExtra("quantity", String.valueOf(item.quantity));
        myIntent.putExtra("date", item.date);
        startActivity(myIntent);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("listofproducts", myCashRegister.listOfProducts);
    }

   //??????????????
    public void onBackPressed(){
        Intent myIntent = new Intent(this,Manager_Activity.class);
        Bundle outState = new Bundle();
        outState.putParcelableArrayList("history", myCashRegister.listOfProducts);
        myIntent.putExtra("bundle", outState);
        startActivity(myIntent);
        return;

    }


}
