package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Product implements Parcelable {

    public  String name;
    public  String price;
    public  String quantity;
    public String date;

    public Product(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = "";

    }

    public Product(String name, String price, String quantity , String date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readString();
        quantity = in.readString();
        date = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(quantity);
        parcel.writeString(date);
    }
}
