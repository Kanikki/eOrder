package com.niki.eorder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niki.eorder.R;
import com.niki.eorder.model.Foodcourt;

import java.util.List;

public class FoodcourtAdapter extends RecyclerView.Adapter<FoodcourtAdapter.FoodcourtViewHolder> {
    private Context context;
    private List<Foodcourt> foodcourtList;

    public FoodcourtAdapter(Context context, List<Foodcourt> foodcourtList){
        this.context = context;
        this.foodcourtList = foodcourtList;
    }

    @NonNull
    @Override
    public FoodcourtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodcourtViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_order_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodcourtAdapter.FoodcourtViewHolder holder, int position) {
        Foodcourt foodcourt = foodcourtList.get(position);


    }

    @Override
    public int getItemCount() {
        return foodcourtList.size();
    }

    class FoodcourtViewHolder extends RecyclerView.ViewHolder {

        public FoodcourtViewHolder(View itemView) {
            super(itemView);
        }
    }
}
