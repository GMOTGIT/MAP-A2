package com.example.assignment2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CashRegister {

    ArrayList<String> quantityWanted = new ArrayList<>();
    ArrayList<Product> listOfProducts;
    ArrayList<Product> HistoryList;


    public CashRegister (){
        HistoryList = new ArrayList<Product>();
        listOfProducts = new ArrayList<Product>();
        listOfProducts.add(new Product ("Pante", "20.44", "10"));
        listOfProducts.add(new Product ("Shoes", "10.44", "100"));
        listOfProducts.add(new Product ("Hats", "5.9", "30"));

    }



    void push (String value){
        quantityWanted.add(value);
    }

    void addHistory (String name, String total, String quantity){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String date = df.format(today);
        HistoryList.add(new Product(name, total, quantity, date ));
    }

    String getQuantity (int position){
        return listOfProducts.get(position).quantity;
    }

    String getName (int position){
        return listOfProducts.get(position).name;
    }

    String getPrice(int position){
        return listOfProducts.get(position).price;
    }


    void setQuantity (int position, int quantity){
        int originalValue = Integer.parseInt(listOfProducts.get(position).quantity);
        int result = originalValue - quantity;
       listOfProducts.get(position).quantity = String.valueOf(result) ;
    }


    String getQuantityWanted(){

        StringBuilder result = new StringBuilder();
        for(String s : quantityWanted){
            result.append(s);
        }

        return result.toString();
    }

}
