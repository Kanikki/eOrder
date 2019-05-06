package com.niki.eorder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niki.eorder.R;
import com.niki.eorder.model.Cart;

import java.util.ArrayList;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListViewHolder> {
    private Context context;
    private ArrayList<Cart> carts;

    public BillListAdapter(Context context, ArrayList<Cart> carts){
        this.context = context;
        this.carts = carts;
    }


    @NonNull
    @Override
    public BillListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_bill_list, parent, false);

        return new BillListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillListViewHolder holder, int position) {
        holder.qty.setText(carts.get(position).getQty());
        holder.name.setText(carts.get(position).getName());
        holder.price.setText(carts.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class BillListViewHolder extends RecyclerView.ViewHolder {
        private TextView qty, name, price;

        public BillListViewHolder(View itemView) {
            super(itemView);

            qty = itemView.findViewById(R.id.bill_qty);
            name = itemView.findViewById(R.id.bill_name);
            price = itemView.findViewById(R.id.bill_price);
        }
    }
}
