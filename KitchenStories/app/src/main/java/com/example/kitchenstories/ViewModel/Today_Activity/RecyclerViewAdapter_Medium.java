package com.example.kitchenstories.ViewModel.Today_Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Medium extends RecyclerView.Adapter<RecyclerViewAdapter_Medium.MyViewHolder> {

    Context mContext;
    List<Recipe> mData;

    public RecyclerViewAdapter_Medium(Context mContext, ArrayList<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_medium, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.image_recipe.setImageResource(mData.get(position).getImage_CookingRecipe());
        holder.image_author.setImageResource(mData.get(position).getImage_author());
        holder.name_recipe.setText(mData.get(position).getName_cooking_recipe());
        holder.name_author.setText(mData.get(position).getName_author());
        holder.name_authorGroup.setText(mData.get(position).getName_authorGroup());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, CookingRecipe.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe;
        private TextView name_recipe;
        private ImageView image_author;
        private TextView name_author;
        private TextView name_authorGroup;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_recipe = (ImageView) itemView.findViewById(R.id.image_recipe_part1_today_activity);
            image_author = (ImageView) itemView.findViewById(R.id.image_author_part1_today_activity);
            name_recipe = (TextView) itemView.findViewById(R.id.name_recipe_part1_today_activity);
            name_author = (TextView) itemView.findViewById(R.id.name_author_part1_today_activity);
            name_authorGroup = (TextView) itemView.findViewById(R.id.name_authorGroup_part1_today_activity);
        }
    }
}
