package com.example.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
    ArrayList<Product> listOfHistory;
    Context mContext;

    public interface OnItemClickListener{
        void onHistoryClicked(Product item);
    }

    private final OnItemClickListener listener;

    public HistoryAdapter(ArrayList<Product> listOfHistory, Context context,OnItemClickListener listenerFromActivity) {
        this.listOfHistory = listOfHistory;
        this.mContext = context;
        listener = listenerFromActivity;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView nameText;
        private final TextView quantityText;
        private final TextView totalText;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.recycler_product_name);
            quantityText = itemView.findViewById(R.id.recycler_quantity);
            totalText = itemView.findViewById(R.id.recycler_total);
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getQuantityText() {
            return quantityText;
        }

        public TextView getTotalText() {
            return totalText;
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater =  LayoutInflater.from(mContext);
        View view =  myInflater.inflate(R.layout.recycler_view_list_row,parent,false);
        return new viewHolder(view);
    }

     @Override
     public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
         holder.getNameText().setText(listOfHistory.get(position).name);
         holder.getQuantityText().setText(String.valueOf(listOfHistory.get(position).quantity));
         holder.getTotalText().setText(String.valueOf(listOfHistory.get(position).price));

         holder.itemView.setOnClickListener(view -> listener.onHistoryClicked(listOfHistory.get(position)));
     }

    @Override
    public int getItemCount() {
        return listOfHistory.size();
    }
}