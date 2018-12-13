package com.example.joel.apprestaurant.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joel.apprestaurant.items.ItemMenu;

import java.util.ArrayList;

i

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private static final android.support.design.R.attr R = ;
    private Context context;
    private ArrayList<ItemMenu> listData;

    public MenuAdapter(Context context, ArrayList<ItemMenu> listData){
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.support.design.R.attr.layout.ItemMenu, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, final int position) {
        //holder.setData(listData.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView idrestaurant, name , price;
        ImageView picture;
        private ConstraintLayout parentLayout;

        public MenuViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNombreMe);
            price = itemView.findViewById(R.id.textPrecioMe);
            picture = itemView.findViewById(R.id.image_view_Me);

            parentLayout = itemView.findViewById(R.id.parent_layoud);

        }
        public void setData(ItemMenu item){
            name.setText(item.getName());
            price.setText(item.getPrice().toString());

        }

    }