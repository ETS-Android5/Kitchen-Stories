package com.example.kitchenstories.ViewModel.CookingRecipeActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.StepsForRecipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_Option_Steps extends FirestoreRecyclerAdapter<StepsForRecipe, RecyclerViewAdapter_Option_Steps.MyViewHolder> {

    Context mContext;

    public RecyclerViewAdapter_Option_Steps(@NonNull FirestoreRecyclerOptions<StepsForRecipe> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position,@NotNull StepsForRecipe model) {

        //Log.d("TestFireStore")
        //holder.tv_numberOfSteps_Steps_Cooking_Activity.setText(model.get);

        holder.tv_numberOfSteps_Steps_Cooking_Activity.setText("STEP " + model.getStep());
        holder.txt_ingredients_Steps_CookingRecipe_Activity.setText(model.getIngredientsForPerStep());
        holder.txt_utensils_Steps_CookingRecipe_Activity.setText(model.getUtensilsForPerStep());
        holder.txt_description_Steps_CookingRecipe_Activity.setText(model.getScriptForDescription());

        Glide.with(mContext)
                .load(model.getUrl_image())
                .into(holder.image_Steps_CookingRecipe_Activity);


    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_steps_cookingrecipe, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_numberOfSteps_Steps_Cooking_Activity;
        private TextView txt_ingredients_Steps_CookingRecipe_Activity;
        private TextView txt_utensils_Steps_CookingRecipe_Activity;
        private TextView txt_description_Steps_CookingRecipe_Activity;
        private ImageView image_Steps_CookingRecipe_Activity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_numberOfSteps_Steps_Cooking_Activity = itemView.findViewById(R.id.tv_numberOfSteps_Steps_Cooking_Activity);
            txt_ingredients_Steps_CookingRecipe_Activity = itemView.findViewById(R.id.txt_ingredients_Steps_CookingRecipe_Activity);
            txt_utensils_Steps_CookingRecipe_Activity = itemView.findViewById(R.id.txt_utensils_Steps_CookingRecipe_Activity);
            txt_description_Steps_CookingRecipe_Activity = itemView.findViewById(R.id.txt_description_Steps_CookingRecipe_Activity);
            image_Steps_CookingRecipe_Activity = itemView.findViewById(R.id.image_Steps_CookingRecipe_Activity);

        }
    }
}
