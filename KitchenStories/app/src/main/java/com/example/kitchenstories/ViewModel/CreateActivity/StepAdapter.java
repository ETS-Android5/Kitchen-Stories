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
import com.example.kitchenstories.Model.recipe.StepsForRecipe;
import com.example.kitchenstories.R;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.MyViewHolder> {

    Context context;
    private List<StepsForRecipe> stepList;

    public StepAdapter(Context mcontext,List<StepsForRecipe> stepList) {
        this.context = mcontext;
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_step_create,parent,false);
        return new StepAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StepsForRecipe step = stepList.get(position);
        Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/loginapp-319bc.appspot.com/o/Recipe%2FRecipe30%2F2.jpg?alt=media&token=2b866a2a-bfd8-48ec-b501-dd1e018121e3").into(holder.image_step);
        holder.name_step.setText("test");
        holder.des_step.setText("step.getScriptForDescription()");
    }

    @Override
    public int getItemCount() {
        return 0;
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
