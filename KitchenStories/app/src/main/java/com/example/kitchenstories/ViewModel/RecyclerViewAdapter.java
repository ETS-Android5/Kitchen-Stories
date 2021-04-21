package com.example.kitchenstories.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.Model.Contact;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Cooking_Recipes;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mData;

    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.items_contact, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_name.setText(mData.get(position).getName());
        holder.txt_phone.setText(mData.get(position).getPhone());
        holder.ima.setImageResource(mData.get(position).getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Cooking_Recipes.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_name;
        private TextView txt_phone;
        private ImageView ima;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.name_contact);
            txt_phone = (TextView) itemView.findViewById(R.id.phone_contact);
            ima = (ImageView) itemView.findViewById(R.id.ima_contact);

        }
    }
}
