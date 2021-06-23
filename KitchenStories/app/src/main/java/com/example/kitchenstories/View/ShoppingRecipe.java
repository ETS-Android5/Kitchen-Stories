package com.example.kitchenstories.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitchenstories.Model.DBHelper;
import com.example.kitchenstories.Model.Shopping.IngredientShopping;
import com.example.kitchenstories.Model.Shopping.RecipeForShopping;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.ShoppingActivity.IngredientAdapter;

import java.util.List;
import java.util.Objects;

public class ShoppingRecipe extends AppCompatActivity {

    private int idShoppingRecipe;
    private DBHelper dbHelper;

    private Toolbar toolbar;

    private TextView textView_Name_Recipe;
    private ImageView imageView_Image_Recipe;
    private RecyclerView rcvIngredient;

    private IngredientAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_recipe);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Transparent status bar
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Transparent navigation bar
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Gray50));
        }

        if(getIntent().hasExtra("ID_SHOPPING_RECIPE")){
            idShoppingRecipe = getIntent().getExtras().getInt("ID_SHOPPING_RECIPE");
        }

        toolbar = findViewById(R.id.toolbar_ShoppingRecipe_Activity);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShoppingRecipe.this,Shopping.class);
                startActivity(i);
            }
        });
        dbHelper = new DBHelper(this);

        textView_Name_Recipe = findViewById(R.id.name_recipe_collapsingToolbar_ShoppingRecipe_Activity);
        imageView_Image_Recipe = findViewById(R.id.image_recipe_collapsingToolbar_ShoppingRecipe_Activity);
        RecipeForShopping ingredientShopping = dbHelper.getRecipeForShoppingByID(idShoppingRecipe);
        textView_Name_Recipe.setText(ingredientShopping.getName());
        imageView_Image_Recipe.setImageBitmap(ingredientShopping.getImage());

        rcvIngredient = findViewById(R.id.rcv_ingredient);
        LoadData();
    }

    private List<IngredientShopping> GetListIngre(){
        return dbHelper.getListIngredient(idShoppingRecipe);
    }

    private void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvIngredient.setLayoutManager(linearLayoutManager);

        adapter = new IngredientAdapter(this,GetListIngre());
        rcvIngredient.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvIngredient.addItemDecoration(itemDecoration);

    }

}