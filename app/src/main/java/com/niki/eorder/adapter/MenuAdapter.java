package com.niki.eorder.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niki.eorder.R;
import com.niki.eorder.model.Menu;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private ArrayList<Menu> menuList;

    public MenuAdapter(Context context, ArrayList<Menu> menuList){
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_menu_card, viewGroup, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder menuViewHolder, final int i) {
        menuViewHolder.tvName.setText(menuList.get(i).getName());
        menuViewHolder.tvPrice.setText("IDR " + Integer.toString(menuList.get(i).getPrice()));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void setData(ArrayList<Menu> menuList){
        this.menuList = menuList;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvPrice;
        private CardView cardView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_menu_name);
            tvPrice = itemView.findViewById(R.id.tv_menu_price);
            cardView = itemView.findViewById(R.id.cv_menu_card);
        }
    }
}
