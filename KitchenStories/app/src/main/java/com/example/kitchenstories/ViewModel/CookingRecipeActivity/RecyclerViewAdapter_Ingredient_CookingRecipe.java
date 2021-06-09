package com.example.kitchenstories.ViewModel.CookingRecipeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter_Ingredient_CookingRecipe extends RecyclerView.Adapter <RecyclerViewAdapter_Ingredient_CookingRecipe.MyViewHolder>{

    Context mContext;
    ArrayList<String> mData;

    public RecyclerViewAdapter_Ingredient_CookingRecipe(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_ingredients_cookingrecipe, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        holder.tv_item_ingredent_Cooking_Recipe.setText(mData.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_item_ingredent_Cooking_Recipe;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_item_ingredent_Cooking_Recipe = itemView.findViewById(R.id.tv_item_ingredent_Cooking_Recipe);
        }
    }
}
