package com.example.kitchenstories.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_OptionFireStore extends FirestoreRecyclerAdapter<Recipe, RecyclerViewAdapter_OptionFireStore.MyViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    Context mContext;
    OnItemClickListener listener;

    public RecyclerViewAdapter_OptionFireStore(Context mContext, @NonNull @NotNull FirestoreRecyclerOptions<Recipe> options, OnItemClickListener listener) {
        super(options);
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull Recipe model) {

        Glide.with(mContext)
                .load(model.getUrl_image_CookingRecipe())
                .into(holder.image_cooking_recipe);
        Glide.with(mContext)
                .load(model.getUrl_image_author())
                .into(holder.image_author);

        holder.name_cooking_recipe.setText(model.getName_cooking_recipe());
        holder.author_recipe.setText(model.getName_author());
        holder.authorGroup_recipe.setText(model.getName_authorGroup());

        int countPeriodTime = Integer.valueOf(model.getPeriodCooking().get(0))
                + Integer.valueOf(model.getPeriodCooking().get(1))
                + Integer.valueOf(model.getPeriodCooking().get(2));



        holder.tv_periodCooking_itemRecipe.setText(String.valueOf(countPeriodTime) + " mins.");

        holder.btn_likeAmount_itemRecipe.setText(model.getLikeAmount());

        if(listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            });
        }

    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_cooking_recipe;
        private ImageView image_author;
        private TextView name_cooking_recipe;
        private TextView author_recipe;
        private TextView authorGroup_recipe;
        private TextView tv_periodCooking_itemRecipe;
        private Button btn_likeAmount_itemRecipe;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_cooking_recipe = itemView.findViewById(R.id.image_cooking_recipe);
            image_author = itemView.findViewById(R.id.image_author);
            name_cooking_recipe = itemView.findViewById(R.id.name_cooking_recipe);
            author_recipe = itemView.findViewById(R.id.author_recipe);
            authorGroup_recipe = itemView.findViewById(R.id.authorGroup_recipe);
            tv_periodCooking_itemRecipe = itemView.findViewById(R.id.tv_periodCooking_itemRecipe);
            btn_likeAmount_itemRecipe = itemView.findViewById(R.id.btn_likeAmount_itemRecipe);
        }
    }
}
