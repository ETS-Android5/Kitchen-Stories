package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe.Likes;
import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.Model.Recipe.StepsForRecipe;
import com.example.kitchenstories.Model.User;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Ingredient_CookingRecipe;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Option_Steps;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_Option_Comment;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CookingRecipe extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView_MoreRecipes;
    private RecyclerViewAdapter_OptionFireStore adapter_optionFireStore_MoreRecipe;
    private ArrayList<Recipe> mData;


    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    Boolean isCheckGlobal = false;

    private Button btn_numberOfLikes_CookingRecipe_Activity;
    private TextView numberOfRating_CookingRecipe_Activity;

    private ImageView image_recipe_collapsingToolbar_CookingRecipe_Activity;
    private TextView name_recipe_collapsingToolbar_CookingRecipe_Activity;

    private ImageView image_author_CookingRecipe_Activity;
    private TextView name_author_CookingRecipe_Activity;
    private TextView name_authorGroup_CookingRecipe_Activity;
    private TextView name_authorContact_CookingRecipe_Activity;
    private TextView txt_authorStory_CookingRecipe_Activity;

    private TextView tv_difficulty_Level_Recipe_CookingRecipe;
    private TextView txt_utensils_Utensils_CookingRecipe_Activity;


    private RecyclerView recyclerView_amountOfIngredients_CookingRecipe;
    private RecyclerViewAdapter_Ingredient_CookingRecipe adapter_amountOfIngredients_cookingRecipe;

    private RecyclerView recyclerView_Ingredients_CookingRecipe;
    private RecyclerViewAdapter_Ingredient_CookingRecipe adapter_ingredient_cookingRecipe;

    private TextView txt_CookingTime_Time_CookingRecipe_Activity;
    private TextView txt_BakingTime_Time_CookingRecipe_Activity;
    private TextView txt_RestingTime_Time_CookingRecipe_Activity;

    private TextView txt_CalAmount_Nutrition_CookingRecipe_Activity;
    private TextView txt_ProteinAmount_Nutrition_CookingRecipe_Activity;
    private TextView txt_FatAmount_Nutrition_CookingRecipe_Activity;
    private TextView txt_CarbAmount_Nutrition_CookingRecipe_Activity;

    private TextView txt_Tags_CookingRecipe_Activity;

    private RecyclerView recyclerView_Steps_CookingRecipe;
    private RecyclerViewAdapter_Option_Steps adapterOptionSteps;

    private ImageView image_FinalRecipe_CookingRecipe_Activity;

    private ImageView image_noComment_CookingRecipe;
    private TextView tv_noComment_CookingRecipe;
    private TextView txt_numberOfComment_Reviews_CookingRecipe_Activity;
    private RecyclerView recyclerView_Comment_TodayActivity;
    private RecyclerViewAdapter_Option_Comment adapterOptionComment;

    EditText edt_comment_CookingRecipe;
    ImageButton btn_comment_CookingRecipe;

    private String idRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_recipe);

        //TransparentStatusAndNavigation
        //transparentStatusAndNavigation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Transparent status bar
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Transparent navigation bar
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Gray50));
        }

        //set up notitle
        toolbar = findViewById(R.id.toolbar_CookingRecipe_Activity);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        // get KeyID_Recipe for each Cooking Recipe
        if(getIntent().hasExtra("KeyID_Recipe")){
            idRecipe = getIntent().getExtras().getString("KeyID_Recipe");
        }



        //
        findByIdForComponents();

        //
        setDataForComponents(idRecipe);

        //
        setDataForComponents_Steps(idRecipe);

        //
        // set btn selected
        onBindDataToBtn(idRecipe);

        // click like
        addLikeRecipe(idRecipe);

        // get comment
        getDataComment(idRecipe);

        // send comment
        sendCommentToFirebase(idRecipe);


        //String str = txt_FinalTags_CookingRecipe_Activity.getText().toString();
        //
        // random to get different recipes
        int min = 1;
        int max = 9;
        int random = (int)(Math.random()*(max-min+1)+min);

        setDataForRecyclerViewRecommend(String.valueOf(random));


    }

    public void findByIdForComponents(){

        btn_numberOfLikes_CookingRecipe_Activity = findViewById(R.id.btn_numberOfLikes_CookingRecipe_Activity);
        numberOfRating_CookingRecipe_Activity = findViewById(R.id.numberOfRating_CookingRecipe_Activity);

        image_recipe_collapsingToolbar_CookingRecipe_Activity = findViewById(R.id.image_recipe_collapsingToolbar_CookingRecipe_Activity);
        name_recipe_collapsingToolbar_CookingRecipe_Activity = findViewById(R.id.name_recipe_collapsingToolbar_CookingRecipe_Activity);

        image_author_CookingRecipe_Activity = findViewById(R.id.image_author_CookingRecipe_Activity);
        name_author_CookingRecipe_Activity = findViewById(R.id.name_author_CookingRecipe_Activity);
        name_authorGroup_CookingRecipe_Activity = findViewById(R.id.name_authorGroup_CookingRecipe_Activity);
        name_authorContact_CookingRecipe_Activity = findViewById(R.id.name_authorContact_CookingRecipe_Activity);
        txt_authorStory_CookingRecipe_Activity = findViewById(R.id.txt_authorStory_CookingRecipe_Activity);

        tv_difficulty_Level_Recipe_CookingRecipe = findViewById(R.id.tv_difficulty_Level_Recipe_CookingRecipe);
        txt_utensils_Utensils_CookingRecipe_Activity = findViewById(R.id.txt_utensils_Utensils_CookingRecipe_Activity);

        recyclerView_amountOfIngredients_CookingRecipe = findViewById(R.id.recyclerView_amountOfIngredients_CookingRecipe);
        recyclerView_Ingredients_CookingRecipe = findViewById(R.id.recyclerView_Ingredients_CookingRecipe);

        txt_CookingTime_Time_CookingRecipe_Activity = findViewById(R.id.txt_CookingTime_Time_CookingRecipe_Activity);
        txt_BakingTime_Time_CookingRecipe_Activity = findViewById(R.id.txt_BakingTime_Time_CookingRecipe_Activity);
        txt_RestingTime_Time_CookingRecipe_Activity = findViewById(R.id.txt_RestingTime_Time_CookingRecipe_Activity);

        txt_CalAmount_Nutrition_CookingRecipe_Activity = findViewById(R.id.txt_CalAmount_Nutrition_CookingRecipe_Activity);
        txt_ProteinAmount_Nutrition_CookingRecipe_Activity = findViewById(R.id.txt_ProteinAmount_Nutrition_CookingRecipe_Activity);
        txt_FatAmount_Nutrition_CookingRecipe_Activity = findViewById(R.id.txt_FatAmount_Nutrition_CookingRecipe_Activity);
        txt_CarbAmount_Nutrition_CookingRecipe_Activity = findViewById(R.id.txt_CarbAmount_Nutrition_CookingRecipe_Activity);

        txt_Tags_CookingRecipe_Activity = findViewById(R.id.txt_Tags_CookingRecipe_Activity);

        recyclerView_Steps_CookingRecipe = findViewById(R.id.recyclerView_Steps_CookingRecipe);

        image_FinalRecipe_CookingRecipe_Activity = findViewById(R.id.image_FinalRecipe_CookingRecipe_Activity);

        image_noComment_CookingRecipe = findViewById(R.id.image_noComment_CookingRecipe);
        tv_noComment_CookingRecipe = findViewById(R.id.tv_noComment_CookingRecipe);

        image_noComment_CookingRecipe.setVisibility(View.GONE);
        tv_noComment_CookingRecipe.setVisibility(View.GONE);

        txt_numberOfComment_Reviews_CookingRecipe_Activity = findViewById(R.id.txt_numberOfComment_Reviews_CookingRecipe_Activity);
        recyclerView_Comment_TodayActivity = findViewById(R.id.recyclerView_Comment_TodayActivity);

        edt_comment_CookingRecipe = findViewById(R.id.edt_comment_CookingRecipe);
        btn_comment_CookingRecipe = findViewById(R.id.btn_comment_CookingRecipe);


        // FIND VIEW BY ID
        recyclerView_MoreRecipes = findViewById(R.id.recyclerView_MoreRecipes_CookingRecipe_Activity);





    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterOptionSteps.startListening();
        adapter_optionFireStore_MoreRecipe.startListening();
        //adapter_optionFireStore_MoreRecipe_BackUp.startListening();
        adapterOptionComment.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterOptionSteps.stopListening();
        adapter_optionFireStore_MoreRecipe.stopListening();
        //adapter_optionFireStore_MoreRecipe_BackUp.stopListening();
        adapterOptionComment.stopListening();
    }



    public void setDataForComponents(String idRecipe){

        firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists()){
                            Recipe recipe = documentSnapshot.toObject(Recipe.class);

                            //String hello = recipe.getName_cooking_recipe();
                            btn_numberOfLikes_CookingRecipe_Activity.setText(recipe.getLikeAmount());
                            numberOfRating_CookingRecipe_Activity.setText(recipe.getRatingAmount() + " ratings");

                            Glide.with(CookingRecipe.this)
                                    .load(recipe.getUrl_image_CookingRecipe())
                                    .into(image_recipe_collapsingToolbar_CookingRecipe_Activity);

                            name_recipe_collapsingToolbar_CookingRecipe_Activity.setText(recipe.getName_cooking_recipe());

                            Glide.with(CookingRecipe.this)
                                    .load(recipe.getUrl_image_author())
                                    .into(image_author_CookingRecipe_Activity);
                            name_author_CookingRecipe_Activity.setText(recipe.getName_author());
                            name_authorGroup_CookingRecipe_Activity.setText(recipe.getName_authorGroup());
                            name_authorContact_CookingRecipe_Activity.setText(recipe.getContact_author());
                            txt_authorStory_CookingRecipe_Activity.setText(recipe.getAuthor_description());

                            tv_difficulty_Level_Recipe_CookingRecipe.setText(recipe.getDifficulty_Level_Recipe());

                            txt_CookingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(0) + " mins.");
                            txt_BakingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(1) + " mins.");
                            txt_RestingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(2) + " mins.");

                            setupRecyclerView_amountOfIngredients(recipe.getAmountOfIngredients());
                            setupRecyclerView_Ingredients(recipe.getIngredients());

                            txt_utensils_Utensils_CookingRecipe_Activity.setText(recipe.getUtensils());

                            txt_CalAmount_Nutrition_CookingRecipe_Activity.setText(recipe.getNutritionPerServing().get(0));
                            txt_ProteinAmount_Nutrition_CookingRecipe_Activity.setText(recipe.getNutritionPerServing().get(1));
                            txt_FatAmount_Nutrition_CookingRecipe_Activity.setText(recipe.getNutritionPerServing().get(2));
                            txt_CarbAmount_Nutrition_CookingRecipe_Activity.setText(recipe.getNutritionPerServing().get(3));


                            Glide.with(CookingRecipe.this)
                                    .load(recipe.getUrl_image_CookingRecipe())
                                    .into(image_FinalRecipe_CookingRecipe_Activity);

                            // get tags
                            String tags_string = "";
                            Map<String, Boolean> map = new HashMap<>();
                            map = recipe.getTags();

                            for (String item : map.keySet()){
                                tags_string += "#" + item +"     ";

                            }
                            txt_Tags_CookingRecipe_Activity.setText(tags_string);


                        }
                        else{
                            //Log.d("TestGetData", "not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                        //Log.d("TestGetData", e.toString());
                    }
                });


        //adapter_amountOfIngredients_cookingRecipe.notifyDataSetChanged();
        //name_recipe_collapsingToolbar_CookingRecipe_Activity
    }



    // on bind data
    public void onBindDataToBtn(String recipeID){

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
                                checkLikeExist(true);
                                return;
                            }
                        }
                        checkLikeExist(false);
                    }
                });
    }

    public void checkLikeExist(Boolean check){
        isCheckGlobal = check;

        //Log.d("TESTAUTH", recipeID);
        if (isCheckGlobal){
            //Log.d("TESTAUTH", "TRUE!!!");

            btn_numberOfLikes_CookingRecipe_Activity.setSelected(true);
        }
        else {
            //Log.d("TESTAUTH", "FALSE!!!");

            btn_numberOfLikes_CookingRecipe_Activity.setSelected(false);
        }
    }

    // add likeRecipe
    public void addLikeRecipe(String recipeID){

        btn_numberOfLikes_CookingRecipe_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            btn_numberOfLikes_CookingRecipe_Activity.setSelected(true);

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

            btn_numberOfLikes_CookingRecipe_Activity.setSelected(false);

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


    public void addLike(String recipeID){

        firebaseFirestore.collection("Recipe").document(recipeID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        int likeInt = Integer.valueOf(recipe.getLikeAmount());

                        likeInt = likeInt + 1;

                        recipe.setLikeAmount(String.valueOf(likeInt));

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

                        int likeInt = Integer.valueOf(recipe.getLikeAmount());

                        likeInt = likeInt - 1;

                        recipe.setLikeAmount(String.valueOf(likeInt));

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
                        String likeAmount = recipe.getLikeAmount();
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

    public void getDataComment(String recipeID){

        recyclerView_Comment_TodayActivity.setLayoutManager(new LinearLayoutManager(CookingRecipe.this, LinearLayoutManager.VERTICAL, false));

        Query query = firebaseFirestore.collection("Recipe").document(recipeID)
                .collection("Review");


        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int count =0;

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    count ++;
                }
                txt_numberOfComment_Reviews_CookingRecipe_Activity.setText(count + " comments");

                if (count == 0){
                    image_noComment_CookingRecipe.setVisibility(View.VISIBLE);
                    tv_noComment_CookingRecipe.setVisibility(View.VISIBLE);
                }
            }
        });

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapterOptionComment = new RecyclerViewAdapter_Option_Comment(CookingRecipe.this, options);

        recyclerView_Comment_TodayActivity.setAdapter(adapterOptionComment);
    }

    public void sendCommentToFirebase(String recipeID){

        edt_comment_CookingRecipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edt_comment_CookingRecipe.getRight() - edt_comment_CookingRecipe.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        edt_comment_CookingRecipe.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        btn_comment_CookingRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cmt = edt_comment_CookingRecipe.getText().toString();
                edt_comment_CookingRecipe.setText("");

                saveComment(recipeID,cmt);

                Toast.makeText(CookingRecipe.this, "Successful comment!!!", Toast.LENGTH_SHORT).show();

                closeKeyboard();
            }
        });
    }

    private void closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    public void saveComment(String recipeID,String stringComment){

        String url_imageUser = firebaseUser.getPhotoUrl().toString();
        String displayNameUser = firebaseUser.getDisplayName();

        User user = new User(displayNameUser, url_imageUser, stringComment);

        firebaseFirestore.collection("Recipe").document(recipeID)
                .collection("Review").add(user);
    }

    public void setDataForComponents_Steps(String idRecipe){

        Query query = firebaseFirestore.collection("Recipe").document(idRecipe).collection("Steps");

        FirestoreRecyclerOptions<StepsForRecipe> options = new FirestoreRecyclerOptions.Builder<StepsForRecipe>()
                .setQuery(query, StepsForRecipe.class)
                .build();


        recyclerView_Steps_CookingRecipe.setLayoutManager(new LinearLayoutManager(CookingRecipe.this, LinearLayoutManager.VERTICAL, false));
        adapterOptionSteps = new RecyclerViewAdapter_Option_Steps(options, CookingRecipe.this);
        recyclerView_Steps_CookingRecipe.setAdapter(adapterOptionSteps);


    }

    public void setupRecyclerView_amountOfIngredients(ArrayList<String> mData){

        ArrayList<String> mData_amountOfIngredients = new ArrayList<>();
        mData_amountOfIngredients = mData;
        recyclerView_amountOfIngredients_CookingRecipe.setLayoutManager(new LinearLayoutManager(CookingRecipe.this, LinearLayoutManager.VERTICAL, false));
        recyclerView_amountOfIngredients_CookingRecipe.setHasFixedSize(true);
        adapter_amountOfIngredients_cookingRecipe = new RecyclerViewAdapter_Ingredient_CookingRecipe(this, mData_amountOfIngredients);
        recyclerView_amountOfIngredients_CookingRecipe.setAdapter(adapter_amountOfIngredients_cookingRecipe);

    }

    public void setupRecyclerView_Ingredients(ArrayList<String> mData){

        ArrayList<String> mData_Ingredients = new ArrayList<>();
        mData_Ingredients = mData;
        recyclerView_Ingredients_CookingRecipe.setLayoutManager(new LinearLayoutManager(CookingRecipe.this, LinearLayoutManager.VERTICAL, false));
        recyclerView_Ingredients_CookingRecipe.setHasFixedSize(true);
        adapter_ingredient_cookingRecipe = new RecyclerViewAdapter_Ingredient_CookingRecipe(this, mData_Ingredients);
        recyclerView_Ingredients_CookingRecipe.setAdapter(adapter_ingredient_cookingRecipe);

    }


    public void setDataForRecyclerViewRecommend(String likeAmount){


        // RecyclerView_MoreRecipes
        recyclerView_MoreRecipes.setLayoutManager(new GridLayoutManager(this, 2));


        Log.d("TESTTAGS", "recyclerview: "+likeAmount);

        Query query = firebaseFirestore.collection("Recipe")
                .whereGreaterThanOrEqualTo("likeAmount", likeAmount)
                .limit(6);

        FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapter_optionFireStore_MoreRecipe = new RecyclerViewAdapter_OptionFireStore(CookingRecipe.this, options, new RecyclerViewAdapter_OptionFireStore.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getApplicationContext(), CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_MoreRecipes.setAdapter(adapter_optionFireStore_MoreRecipe);


        //Log.d("TESTTAG", likeAmount);
    }




    private void initData(){

        mData = new ArrayList<>();

        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    // Transparent Status Bar
    public void transparentStatusAndNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {

            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );

        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}