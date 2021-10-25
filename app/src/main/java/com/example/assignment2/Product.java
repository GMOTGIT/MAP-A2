package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    public  String name;
    public  double price;
    public  int quantity;
    public String date;

    public Product(String name, String price, String quantity) {
        this.name = name;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(quantity);
        this.date = "";
    }

    public Product(String name, String price, String quantity , String date) {
        this.name = name;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(quantity);
        this.date = date;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
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
        parcel.writeDouble(price);
        parcel.writeInt(quantity);
        parcel.writeString(date);
    }
}
