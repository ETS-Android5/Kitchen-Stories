package com.example.kitchenstories.ViewModel.Today_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_part13 extends RecyclerView.Adapter<RecyclerViewAdapter_part13.MyViewHolder> {

    Context mContext;
    ArrayList<Recipe> mData;

    public RecyclerViewAdapter_part13(Context mContext, ArrayList<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingredient_recipe_medium,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.image_recipe.setImageResource(mData.get(position).getImage_CookingRecipe());
        holder.name_recipe.setText(mData.get(position).getName_cooking_recipe());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe;
        private TextView name_recipe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_recipe = (ImageView) itemView.findViewById(R.id.image_recipe_part13_today_activity);
            name_recipe = (TextView) itemView.findViewById(R.id.name_recipe_part13_today_activity);
        }
    }
}
