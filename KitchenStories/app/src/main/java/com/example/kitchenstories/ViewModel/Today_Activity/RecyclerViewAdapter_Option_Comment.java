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
import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.Model.User;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewAdapter_Option_Comment extends FirestoreRecyclerAdapter<User, RecyclerViewAdapter_Option_Comment.MyViewHolder> {

    Context mContext;

    public RecyclerViewAdapter_Option_Comment(Context mContext, @NonNull @NotNull FirestoreRecyclerOptions<User> options) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull User model) {

        Glide.with(mContext)
                .load(model.getUrl_imageUser())
                .into(holder.image_User_Comment);

        holder.name_User_Comment.setText(model.getDisplayNameUser());
        holder.discription_User_Comment.setText(model.getStringComment());


    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image_User_Comment;
        TextView name_User_Comment;
        TextView discription_User_Comment;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_User_Comment = itemView.findViewById(R.id.image_User_Comment);
            name_User_Comment = itemView.findViewById(R.id.name_User_Comment);
            discription_User_Comment = itemView.findViewById(R.id.discription_User_Comment);
        }
    }
}
