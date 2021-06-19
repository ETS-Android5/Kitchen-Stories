package com.example.kitchenstories.ViewModel.Today_Activity;

import android.content.Context;
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
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_Option_Medium extends FirestoreRecyclerAdapter<Recipe, RecyclerViewAdapter_Option_Medium.MyViewHolder> {

    Context mContext;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public RecyclerViewAdapter_Option_Medium(Context mContext, @NonNull @NotNull FirestoreRecyclerOptions<Recipe> options, OnItemClickListener listener) {
        super(options);
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull Recipe model) {

        Glide.with(mContext)
                .load(model.getUrl_image_CookingRecipe())
                .into(holder.image_recipe);
        Glide.with(mContext)
                .load(model.getUrl_image_author())
                .into(holder.image_author);

        holder.name_recipe.setText(model.getName_cooking_recipe());
        holder.name_author.setText(model.getName_author());
        holder.name_authorGroup.setText(model.getName_authorGroup());

        int countPeriodTime = Integer.valueOf(model.getPeriodCooking().get(0))
                + Integer.valueOf(model.getPeriodCooking().get(1))
                + Integer.valueOf(model.getPeriodCooking().get(2));


        holder.tv_periodCooking_part1_today_activity.setText(String.valueOf(countPeriodTime) + " mins.");

        holder.btn_likeAmount_part1_today_activity.setText(model.getLikeAmount());
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_medium, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe;
        private TextView name_recipe;
        private ImageView image_author;
        private TextView name_author;
        private TextView name_authorGroup;
        private TextView tv_periodCooking_part1_today_activity;
        private Button btn_likeAmount_part1_today_activity;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image_recipe = (ImageView) itemView.findViewById(R.id.image_recipe_part1_today_activity);
            image_author = (ImageView) itemView.findViewById(R.id.image_author_part1_today_activity);
            name_recipe = (TextView) itemView.findViewById(R.id.name_recipe_part1_today_activity);
            name_author = (TextView) itemView.findViewById(R.id.name_author_part1_today_activity);
            name_authorGroup = (TextView) itemView.findViewById(R.id.name_authorGroup_part1_today_activity);
            tv_periodCooking_part1_today_activity = (TextView) itemView.findViewById(R.id.tv_periodCooking_part1_today_activity);
            btn_likeAmount_part1_today_activity = (Button) itemView.findViewById(R.id.btn_likeAmount_part1_today_activity);

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
