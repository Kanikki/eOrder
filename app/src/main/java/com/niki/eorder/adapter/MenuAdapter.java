package com.niki.eorder.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.niki.eorder.R;
import com.niki.eorder.model.Cart;
import com.niki.eorder.model.Menu;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private ArrayList<Menu> menuList;
    private ArrayList<Cart> cartList;

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
        final int[] qty = {0};
        menuViewHolder.tvName.setText(menuList.get(i).getName());
        menuViewHolder.tvPrice.setText("IDR " + Integer.toString(menuList.get(i).getPrice()));

        menuViewHolder.btnMinQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty[0]--;
                if (qty[0] < 1){
                    qty[0] = 0;
                    menuViewHolder.btnMinQty.setVisibility(View.INVISIBLE);
                }
                menuViewHolder.tvQty.setText(qty[0] + "");
            }
        });

        menuViewHolder.btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty[0]++;
                menuViewHolder.btnMinQty.setVisibility(View.VISIBLE);
                menuViewHolder.tvQty.setText(qty[0] + "");
            }
        });

        if (cartList.size() == 0){
            Cart cart = new Cart();
            cart.setID(menuList.get(i).getID());
            cart.setName(menuList.get(i).getName());
            cart.setPrice(menuList.get(i).getPrice());
            cart.setQty(qty[0]);
        }
        else{
            boolean menuHasAdded = false;
            int index = 0;
            for (int j = 0; j < cartList.size(); j++){
                if (cartList.get(j).getID() == menuList.get(i).getID()){
                    menuHasAdded = true;
                    cartList.get(j).setQty(qty[0]);

                    if (cartList.get(j).getQty() < 1){
                        cartList.remove(j);
                    }
                    break;
                }
            }
            if (!menuHasAdded){
                Cart cart = new Cart();
                cart.setID(menuList.get(i).getID());
                cart.setName(menuList.get(i).getName());
                cart.setPrice(menuList.get(i).getPrice());
                cart.setQty(qty[0]);
            }
        }
    }

    public ArrayList<Cart> getCartItem(){
        return cartList;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvPrice, tvQty;
        private Button btnAddQty, btnMinQty;
        private CardView cardView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_menu_name);
            tvPrice = itemView.findViewById(R.id.tv_menu_price);
            tvQty = itemView.findViewById(R.id.tv_qty);
            btnAddQty = itemView.findViewById(R.id.btn_add_qty);
            btnMinQty = itemView.findViewById(R.id.btn_min_qty);
            cardView = itemView.findViewById(R.id.cv_menu_card);
            cartList = new ArrayList<>();

            tvQty.setText("0");
            btnMinQty.setVisibility(View.INVISIBLE);
        }
    }
}
