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
import com.example.kitchenstories.Model.recipe.Likes;
import com.example.kitchenstories.Model.recipe.Recipe;
import com.example.kitchenstories.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter_Option_Large extends FirestoreRecyclerAdapter<Recipe, RecyclerViewAdapter_Option_Large.MyViewHolder> {

    Context mContext;
    OnItemClickListener listener;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    Boolean isCheckGlobal = false;

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public RecyclerViewAdapter_Option_Large(Context mContext, @NonNull @NotNull FirestoreRecyclerOptions<Recipe> options, OnItemClickListener listener) {
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


        holder.tv_periodCooking_part4_today_activity.setText(String.valueOf(countPeriodTime) + " mins.");

        holder.btn_likeAmount_part4_today_activity.setText(model.getLikeAmount().toString());

        // set btn selected
        String recipeID = getSnapshots().getSnapshot(position).getId();
        onBindDataToBtn(recipeID, holder);

    }

    public void onBindDataToBtn(String recipeID, @NonNull @NotNull MyViewHolder holder){

        String emailUserCurrent = firebaseUser.getEmail();

        firebaseFirestore.collection("Recipe").document(recipeID)
                .collection("Likes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            Likes userLikesCheck = documentSnapshot.toObject(Likes.class);

                            if (userLikesCheck.getEmailUser().equals(emailUserCurrent)) {
                                //checkLikeUserExist(true, recipeID);
                                checkLikeExist(true, holder);
                                return;
                            }
                        }
                        checkLikeExist(false, holder);
                    }
                });
    }

    public void checkLikeExist(Boolean check, @NonNull @NotNull MyViewHolder holder){
        isCheckGlobal = check;

        //Log.d("TESTAUTH", recipeID);
        if (isCheckGlobal){
            //Log.d("TESTAUTH", "TRUE!!!");

            holder.btn_likeAmount_part4_today_activity.setSelected(true);
        }
        else {
            //Log.d("TESTAUTH", "FALSE!!!");

            holder.btn_likeAmount_part4_today_activity.setSelected(false);
        }
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_large, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe;
        private TextView name_recipe;
        private ImageView image_author;
        private TextView name_author;
        private TextView name_authorGroup;
        private TextView tv_periodCooking_part4_today_activity;
        private Button btn_likeAmount_part4_today_activity;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image_recipe = (ImageView) itemView.findViewById(R.id.image_recipe_part4_today_activity);
            image_author = (ImageView) itemView.findViewById(R.id.image_author_part4_today_activity);
            name_recipe = (TextView) itemView.findViewById(R.id.name_recipe_part4_today_activity);
            name_author = (TextView) itemView.findViewById(R.id.name_author_part4_today_activity);
            name_authorGroup = (TextView) itemView.findViewById(R.id.name_authorGroup_part4_today_activity);
            tv_periodCooking_part4_today_activity = (TextView) itemView.findViewById(R.id.tv_periodCooking_part4_today_activity);
            btn_likeAmount_part4_today_activity = (Button) itemView.findViewById(R.id.btn_likeAmount_part4_today_activity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != -1 && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

            btn_likeAmount_part4_today_activity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    String recipeID = getSnapshots().getSnapshot(position).getId();

                    String emailUserCurrent = firebaseUser.getEmail();

                    Task<QuerySnapshot> query = firebaseFirestore.collection("Recipe").document(recipeID)
                            .collection("Likes")
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                        Likes userLikesCheck = documentSnapshot.toObject(Likes.class);

                                        if (userLikesCheck.getEmailUser().equals(emailUserCurrent)) {
                                            checkLikeUserExist(true, recipeID, emailUserCurrent);
                                            return;
                                        }
                                    }
                                    checkLikeUserExist(false, recipeID, emailUserCurrent);
                                }
                            });


                }
            });
        }

        public void checkLikeUserExist(Boolean check, String recipeID, String emailUserCurrent) {

            Boolean isLikeUserExist = check;

            if (!isLikeUserExist) {

                btn_likeAmount_part4_today_activity.setSelected(true);

                String uIDCurrent = firebaseUser.getUid();
                Likes likes = new Likes(uIDCurrent, emailUserCurrent);

                // add uIDCurrent into Recipe
                firebaseFirestore.collection("Recipe").document(recipeID)
                        .collection("Likes").document(emailUserCurrent).set(likes);

                // count like amount
                addLike(recipeID);

                // add recipe into User
                addRecipeModelInToUser(recipeID, emailUserCurrent);


            } else {

                btn_likeAmount_part4_today_activity.setSelected(false);

                // delete emailUserCurrent in Recipe
                firebaseFirestore.collection("Recipe").document(recipeID)
                        .collection("Likes").document(emailUserCurrent).delete();

                // remove like
                removeLike(recipeID);

                // delete recipeLiked in User
                firebaseFirestore.collection("User").document(emailUserCurrent)
                        .collection("RecipeLiked").document(recipeID).delete();
            }
        }
    }

    public void addLike(String recipeID){

        firebaseFirestore.collection("Recipe").document(recipeID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        Long likeInt = recipe.getLikeAmount();

                        likeInt = likeInt + 1;

                        recipe.setLikeAmount(likeInt);

                        // set again;
                        firebaseFirestore.collection("Recipe").document(recipeID).set(recipe);

                    }
                });

    }

    public void removeLike(String recipeID){

        firebaseFirestore.collection("Recipe").document(recipeID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        Long likeInt = recipe.getLikeAmount();

                        likeInt = likeInt - 1;

                        recipe.setLikeAmount(likeInt);

                        // set again;
                        firebaseFirestore.collection("Recipe").document(recipeID).set(recipe);

                    }
                });
    }

    public void addRecipeModelInToUser(String recipeID, String emailUserCurrent){

        firebaseFirestore.collection("Recipe").document(recipeID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        String name_cooking_recipe = recipe.getName_cooking_recipe();
                        String url_image_CookingRecipe = recipe.getUrl_image_CookingRecipe();
                        String name_author = recipe.getName_author();
                        String name_authorGroup = recipe.getName_authorGroup();
                        String url_image_author = recipe.getUrl_image_author();
                        Long likeAmount = recipe.getLikeAmount();
                        ArrayList<String> periodCooking = recipe.getPeriodCooking();

                        Recipe recipeToAdd = new Recipe(name_cooking_recipe,
                                url_image_CookingRecipe,
                                name_author,
                                name_authorGroup,
                                url_image_author,
                                likeAmount,
                                periodCooking);

                        // add
                        firebaseFirestore.collection("User").document(emailUserCurrent).
                                collection("RecipeLiked").document(recipeID)
                                .set(recipeToAdd);

                    }
                });

    }



}
