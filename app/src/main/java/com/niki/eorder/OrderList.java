package com.niki.eorder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niki.eorder.adapter.OrderAdapter;
import com.niki.eorder.model.Cart;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvTotal, tvTax, tvFee, tvGrandTotal;
    private Button btnOrder, btnCancel;
    private OrderAdapter adapter;
    private int total = 0, fee = 100, tax = 0, grandTotal;
    ArrayList<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Bundle bundle = getIntent().getExtras();
        cartList = (ArrayList<Cart>) bundle.getSerializable("dataCart");

        recyclerView = findViewById(R.id.rv_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrderAdapter(OrderList.this, cartList);
        recyclerView.setAdapter(adapter);

        tvTotal = findViewById(R.id.tv_order_total);
        tvTax = findViewById(R.id.tv_order_tax);
        tvFee = findViewById(R.id.tv_order_fee);
        tvGrandTotal = findViewById(R.id.tv_order_grand_total);
        btnOrder = findViewById(R.id.btn_confirm);
        btnCancel = findViewById(R.id.btn_cancel);

        setItemPriceView();

        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                if (total == 0){
                    btnOrder.setBackgroundColor(Color.parseColor("#696969"));
                    btnOrder.setClickable(false);
                }
            }

            @Override
            public void onClickButton() {
                setItemPriceView();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderList.this, Payment.class);
                intent.putExtra("paymentPrice", grandTotal);
                startActivity(intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.clear();
                Toast.makeText(OrderList.this, "Order canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }


    private void setItemPriceView(){
        total = adapter.getTotalPrice();
        tax = total / 10;
        fee = 100;
        grandTotal = total + tax + fee;

        tvTotal.setText("Total: IDR " + String.valueOf(total));
        tvTax.setText("Tax(10%): IDR " + String.valueOf(tax));
        tvFee.setText("Admin Fee: IDR " + String.valueOf(fee));
        tvGrandTotal.setText("Grand Total : IDR " + String.valueOf(grandTotal));
    }

    private void removeItem(int position){
        cartList.remove(position);
        adapter.notifyItemRemoved(position);
        setItemPriceView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderList.this, MenuList.class);
        Toast.makeText(OrderList.this, "Order canceled", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
