package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.kitchenstories.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Button btn_All_recipes;
    private Button btn_search_by_Search_Activity;

    private TextView txtSearch_Search_Activity;
    private Animation animationHide_Button_SearchBy;
    private Animation animationHide_textView;

    private CardView card_Recipes_Pasta;
    private CardView card_Recipes_Asian;
    private CardView card_Recipes_Main;
    private CardView card_Recipes_Quick;
    private CardView card_Recipes_Meatless;
    private CardView card_Recipes_Dessert;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //
        transparentStatusAndNavigation();


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        // find View by ID
        findByIdForComponents();


        // animation
        animationHide_Button_SearchBy = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up_300);
        animationHide_textView = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up_300);


        //
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.today:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        return true;

                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), Create.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.shopping:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        btn_All_recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllRecipesActivity();
            }
        });


        btn_search_by_Search_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFilterInSearchActivity();


            }
        });


        // click cardview to open FilterInSearchAllRecipe activity
        openActivity_CardViewClick();




    }

    public void findByIdForComponents(){

        btn_All_recipes = findViewById(R.id.btn_AllRecipes);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        btn_search_by_Search_Activity = findViewById(R.id.btn_search_by_Search_Activity);

        txtSearch_Search_Activity = findViewById(R.id.txtSearch_Search_Activity);

        card_Recipes_Pasta = findViewById(R.id.card_Recipes_Pasta);
        card_Recipes_Asian = findViewById(R.id.card_Recipes_Asian);
        card_Recipes_Main = findViewById(R.id.card_Recipes_Main);
        card_Recipes_Quick = findViewById(R.id.card_Recipes_Quick);
        card_Recipes_Meatless = findViewById(R.id.card_Recipes_Meatless);
        card_Recipes_Dessert = findViewById(R.id.card_Recipes_Dessert);

    }

    public void openActivity_CardViewClick(){

        card_Recipes_Pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "pasta");
                startActivity(intent);
            }
        });
        card_Recipes_Asian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "asian");
                startActivity(intent);
            }
        });
        card_Recipes_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "main");
                startActivity(intent);
            }
        });
        card_Recipes_Quick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "quick");
                startActivity(intent);
            }
        });
        card_Recipes_Meatless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "meatless");
                startActivity(intent);
            }
        });
        card_Recipes_Dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Search.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "dessert");
                startActivity(intent);
            }
        });


    }

    public void openAllRecipesActivity(){

        Intent intent = new Intent(this, All_recipes.class);
        startActivity(intent);
    }

    public void openFilterInSearchActivity(){
        Intent intent = new Intent(this, FilterInSearch.class);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, btn_search_by_Search_Activity, ViewCompat.getTransitionName(btn_search_by_Search_Activity));

        startActivity(intent, options.toBundle());

//        txtSearch_Search_Activity.startAnimation(animationHide_Button_SearchBy);
//        btn_search_by_Search_Activity.startAnimation(animationHide_textView);

        //overridePendingTransition(R.anim.test2_null,R.anim.test1);
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
            //getWindow().setNavigationBarColor(Color.TRANSPARENT);
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