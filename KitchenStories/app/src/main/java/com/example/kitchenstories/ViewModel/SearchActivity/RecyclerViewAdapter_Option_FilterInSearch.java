package com.example.kitchenstories.ViewModel.SearchActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_Option_FilterInSearch extends FirestoreRecyclerAdapter<Recipe, RecyclerViewAdapter_Option_FilterInSearch.MyViewHolder> {

    public interface OnItemClickListener{
        void onClickListener(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    OnItemClickListener listener;
    Context mContext;

    public RecyclerViewAdapter_Option_FilterInSearch(Context mContext ,@NonNull @NotNull FirestoreRecyclerOptions<Recipe> options, OnItemClickListener listener) {
        super(options);
        this.mContext = mContext;
        this.listener = listener;

    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull Recipe model) {

        Glide.with(mContext)
                .load(model.getUrl_image_CookingRecipe())
                .into(holder.image_item_recipe_filterinsearch_small);

        holder.tv_name_item_recipe_filterinsearch_small.setText(model.getName_cooking_recipe());
        holder.tv_nameGroup_item_recipe_filterinsearch_small.setText(model.getName_authorGroup());
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_filterinsearch_small, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_item_recipe_filterinsearch_small;
        private TextView tv_name_item_recipe_filterinsearch_small;
        private TextView tv_nameGroup_item_recipe_filterinsearch_small;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_item_recipe_filterinsearch_small = itemView.findViewById(R.id.image_item_recipe_filterinsearch_small);
            tv_name_item_recipe_filterinsearch_small = itemView.findViewById(R.id.tv_name_item_recipe_filterinsearch_small);
            tv_nameGroup_item_recipe_filterinsearch_small = itemView.findViewById(R.id.tv_nameGroup_item_recipe_filterinsearch_small);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != -1 && listener != null){
                           listener.onClickListener(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
}
