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
import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Cooking_Recipes;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Recipe> mData;

    public RecyclerViewAdapter(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.image_cooking_recipe.setImageResource(mData.get(position).getImage_CookingRecipe());
        holder.image_author.setImageResource(mData.get(position).getImage_author());
        holder.name_cooking_recipe.setText(mData.get(position).getName_cooking_recipe());
        holder.author_recipe.setText(mData.get(position).getAuthor_recipe());
        holder.authorGroup_recipe.setText(mData.get(position).getAuthorGroup_recipe());


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

        private ImageView image_cooking_recipe;
        private TextView name_cooking_recipe;
        private ImageView image_author;
        private TextView author_recipe;
        private TextView authorGroup_recipe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_cooking_recipe = (ImageView) itemView.findViewById(R.id.image_cooking_recipe);
            image_author = (ImageView) itemView.findViewById(R.id.image_author);
            name_cooking_recipe = (TextView) itemView.findViewById(R.id.name_cooking_recipe);
            author_recipe = (TextView) itemView.findViewById(R.id.author_recipe);
            authorGroup_recipe = (TextView) itemView.findViewById(R.id.authorGroup_recipe);

        }
    }
}
