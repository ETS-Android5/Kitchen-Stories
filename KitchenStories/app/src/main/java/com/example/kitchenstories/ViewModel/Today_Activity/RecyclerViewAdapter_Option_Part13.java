package com.example.kitchenstories.ViewModel.Today_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.TypesOfRecipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_Option_Part13 extends FirestoreRecyclerAdapter<TypesOfRecipe, RecyclerViewAdapter_Option_Part13.MyViewHolder> {

    Context mContext;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public RecyclerViewAdapter_Option_Part13(Context mContext, @NonNull @NotNull FirestoreRecyclerOptions<TypesOfRecipe> options, OnItemClickListener listener) {
        super(options);
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull TypesOfRecipe model) {

        Glide.with(mContext)
                .load(model.getUrl_typedOfRecipe())
                .into(holder.image_recipe);

        holder.name_recipe.setText(model.getNameOfType());

    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingredient_recipe_medium, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe;
        private TextView name_recipe;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_recipe = (ImageView) itemView.findViewById(R.id.image_recipe_part13_today_activity);
            name_recipe = (TextView) itemView.findViewById(R.id.name_recipe_part13_today_activity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != -1 && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
}
