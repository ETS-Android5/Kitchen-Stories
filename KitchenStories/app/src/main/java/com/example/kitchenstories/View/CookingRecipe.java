package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.Model.StepsForRecipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Ingredient_CookingRecipe;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Option_Steps;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class CookingRecipe extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView_MoreRecipes;
    private ArrayList<Recipe> mData;


    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private ImageView image_recipe_collapsingToolbar_CookingRecipe_Activity;
    private TextView name_recipe_collapsingToolbar_CookingRecipe_Activity;
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


        //
        findByIdForComponents();

        //
        setDataForComponents();

        //
        setDataForComponents_Steps();



    }

    public void findByIdForComponents(){

        image_recipe_collapsingToolbar_CookingRecipe_Activity = findViewById(R.id.image_recipe_collapsingToolbar_CookingRecipe_Activity);
        name_recipe_collapsingToolbar_CookingRecipe_Activity = findViewById(R.id.name_recipe_collapsingToolbar_CookingRecipe_Activity);
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


        // FIND VIEW BY ID
        recyclerView_MoreRecipes = findViewById(R.id.recyclerView_MoreRecipes_CookingRecipe_Activity);
        // RecyclerView_MoreRecipes
        recyclerView_MoreRecipes.setLayoutManager(new GridLayoutManager(this, 2));
        initData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mData);
        recyclerView_MoreRecipes.setAdapter(adapter);




    }



    public void setDataForComponents(){

        firebaseFirestore.collection("Recipe").document("Recipe6")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists()){
                            Recipe recipe = documentSnapshot.toObject(Recipe.class);

                            //String hello = recipe.getName_cooking_recipe();

                            Glide.with(CookingRecipe.this)
                                    .load(recipe.getUrl_image_CookingRecipe())
                                    .into(image_recipe_collapsingToolbar_CookingRecipe_Activity);

                            name_recipe_collapsingToolbar_CookingRecipe_Activity.setText(recipe.getName_cooking_recipe());
                            tv_difficulty_Level_Recipe_CookingRecipe.setText(recipe.getDifficulty_Level_Recipe());

                            txt_CookingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(0));
                            txt_BakingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(1));
                            txt_RestingTime_Time_CookingRecipe_Activity.setText(recipe.getPeriodCooking().get(2));

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

                            String tags_string = "";
                            // get tags
                            for(int i=0;i<recipe.getTags().size();i++){
                                tags_string += "#" + recipe.getTags().get(i) +"     ";
                            }
                            txt_Tags_CookingRecipe_Activity.setText(tags_string);


                        }
                        else{
                            Log.d("TestGetData", "not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                        Log.d("TestGetData", e.toString());
                    }
                });


        //adapter_amountOfIngredients_cookingRecipe.notifyDataSetChanged();
        //name_recipe_collapsingToolbar_CookingRecipe_Activity
    }


    public void setDataForComponents_Steps(){

        Query query = firebaseFirestore.collection("Recipe").document("Recipe6").collection("Steps");

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

    @Override
    protected void onStart() {
        super.onStart();
        adapterOptionSteps.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterOptionSteps.stopListening();
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