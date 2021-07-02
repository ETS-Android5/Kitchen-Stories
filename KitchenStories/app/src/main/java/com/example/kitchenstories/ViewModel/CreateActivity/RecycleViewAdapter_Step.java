package com.example.kitchenstories.ViewModel.CreateActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe.StepsForRecipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecycleViewAdapter_Step extends FirestoreRecyclerAdapter<StepsForRecipe,RecycleViewAdapter_Step.MyViewHolder> {

    Context mContext;

    public RecycleViewAdapter_Step(@NonNull FirestoreRecyclerOptions<StepsForRecipe> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull StepsForRecipe model) {
        Glide.with(mContext).load(model.getUrl_image()).into(holder.image_step);
        holder.name_step.setText(model.getStep());
        holder.des_step.setText(model.getScriptForDescription());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_step_create, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_step;
        private TextView name_step;
        private TextView des_step;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_step = itemView.findViewById(R.id.image_step);
            name_step = itemView.findViewById(R.id.name_step);
            des_step = itemView.findViewById(R.id.des_step);

        }
    }
}
