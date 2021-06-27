package com.example.kitchenstories.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kitchenstories.R;
import com.example.kitchenstories.View.filterInSearch_AllRecipe.FilterInSearchAllRecipe;

import java.util.ArrayList;

public class Filter_Recipe extends AppCompatActivity {

    private Toolbar toolbar;

    private Button btn_Done_FilterRecipe;

    private Button button_Sort;
    private Button button_Category;
    private Button button_Diet;
    private Button button_Cuisine;
    private Button button_Occasion;

    private LinearLayout linearlayout_sort;
    private LinearLayout linearlayout_category;
    private LinearLayout linearlayout_diet;
    private LinearLayout linearlayout_cuisine;
    private LinearLayout linearlayout_occasion;

    private TextView txtview_name_category_Filter_Activity;
    private TextView txtview_name_diet_Filter_Activity;
    private TextView txtview_name_cuisine_Filter_Activity;
    private TextView txtview_name_occasion_Filter_Activity;

    private CardView cardView_starter_category_Filter_Activity;
    private CardView cardView_main_category_Filter_Activity;
    private CardView cardView_dessert_category_Filter_Activity;
    private CardView cardView_snack_category_Filter_Activity;
    private CardView cardView_breakfast_category_Filter_Activity;
    private CardView cardView_drink_category_Filter_Activity;

    private CardView cardView_meatless_diet_Filter_Activity;
    private CardView cardView_vegetarian_diet_Filter_Activity;
    private CardView cardView_vegan_diet_Filter_Activity;
    private CardView cardView_glutenFree_diet_Filter_Activity;
    private CardView cardView_sugarFree_diet_Filter_Activity;
    private CardView cardView_alcohol_diet_Filter_Activity;


    private TextView txtView_starter_category_filter_activity;
    private TextView txtView_main_category_filter_activity;
    private TextView txtView_dessert_category_filter_activity;
    private TextView txtView_snack_category_filter_activity;
    private TextView txtView_breakfast_category_filter_activity;
    private TextView txtView_drink_category_filter_activity;

    private TextView txtView_Meatless_diet_filter_activity;
    private TextView txtView_Vegetarian_diet_filter_activity;
    private TextView txtView_Vegan_diet_filter_activity;
    private TextView txtView_GlutenFree_diet_filter_activity;
    private TextView txtView_SugarFree_diet_filter_activity;
    private TextView txtView_AlcoholFree_diet_filter_activity;


    private Button btn_relevance_Sort_Filter_Activity;
    private Button btn_likes_Sort_Filter_Activity;
    private Button btn_rating_Sort_Filter_Activity;
    private Button btn_calories_Sort_Filter_Activity;
    private Button btn_preparationTime_Sort_Filter_Activity;

    private Button btn_Chinese_cuisine_Filter_Activity;
    private Button btn_Italian_cuisine_Filter_Activity;
    private Button btn_European_cuisine_Filter_Activity;
    private Button btn_Asian_cuisine_Filter_Activity;
    private Button btn_American_cuisine_Filter_Activity;
    private Button btn_Spanish_and_Portuguese_cuisine_Filter_Activity;
    private Button btn_Indian_cuisine_Filter_Activity;
    private Button btn_Middle_Eastern_cuisine_Filter_Activity;

    private Button btn_WeeknightDinner_Occasion_Filter_Activity;
    private Button btn_Prepare_ahead_Occasion_Filter_Activity;
    private Button btn_CrowdPleaser_Occasion_Filter_Activity;
    private Button btn_OnTheGo_Occasion_Filter_Activity;
    private Button btn_ComfortFood_Occasion_Filter_Activity;
    private Button btn_KidFriendly_Occasion_Filter_Activity;
    private Button btn_FingerFood_Occasion_Filter_Activity;
    private Button btn_Barbecue_Occasion_Filter_Activity;
    private Button btn_Christmas_Occasion_Filter_Activity;
    private Button btn_Easter_Occasion_Filter_Activity;
    private Button btn_ValentinesDay_Occasion_Filter_Activity;
    private Button btn_Halloween_Occasion_Filter_Activity;
    private Button btn_Octoberfest_Occasion_Filter_Activity;


    private boolean isClicked_linearlayout_sort = false;
    private boolean isClicked_linearlayout_Category = false;
    private boolean isClicked_linearlayout_Diet = false;
    private boolean isClicked_linearlayout_Cuisine = false;

    private TextView txtview_name_sort_Filter_Activity;



    private Animation animationShow;
    private Animation animationHide;

    String sortExtra = "";
    String categoryExtra = "";
    String dietExtra = "";
    String cuisineExtra = "";
    ArrayList<String> occasionExtra = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__recipe);

//
        transparentStatusAndNavigation();

        Window window = Filter_Recipe.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Filter_Recipe.this, R.color.Gray50));




        // FIND VIEW BY ID
        findByIdByComments();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        });


        // // VISIBILITY IS GONE
        setVisibilityGoneLinearLayout();


        // animation
        animationShow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animationHide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.face_out);


        txtview_name_category_Filter_Activity.setText("");
        txtview_name_diet_Filter_Activity.setText("");
        txtview_name_cuisine_Filter_Activity.setText("");
        txtview_name_occasion_Filter_Activity.setText("");

        // PART: SORT
        // VISIBILITY LINEAR LAYOUT
        handlePartSort();


        // PART: CATEGORY
        handlePartCategory();


        // PART: DIET
        handlePartDiet();


        // PART: CUISINE
        handlePartCuisine();


        // PART: Ingredients
        //handlePartIngredients();


        // PART: OCCASION
        handlePartOccasion();


        btn_Done_FilterRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();

                putExtraToFilterInSearchAllRecipe();
            }
        });

        // the end of Create
    }

    public void putExtraToFilterInSearchAllRecipe(){

        Intent intent = new Intent(getApplicationContext(), FilterInSearchAllRecipe.class);
        intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", "Filter");


        //
        intent.putExtra("sortExtra", sortExtra);
        intent.putExtra("categoryExtra", categoryExtra);
        intent.putExtra("dietExtra", dietExtra);
        intent.putExtra("cuisineExtra", cuisineExtra);
        intent.putStringArrayListExtra("occasionExtra", occasionExtra);

        startActivity(intent);
    }


    public void handlePartSort(){

        button_Sort.setOnClickListener(new View.OnClickListener() {
            //boolean isClicked = false;


            @Override
            public void onClick(View v) {

                isClicked_linearlayout_sort = !isClicked_linearlayout_sort;

                if (isClicked_linearlayout_sort){

                    button_Sort.setSelected(true);

                    linearlayout_sort.setVisibility(View.VISIBLE);
                    linearlayout_sort.startAnimation(animationShow);

                }
                else{

                    button_Sort.setSelected(false);

                    linearlayout_sort.startAnimation(animationHide);

                    new CountDownTimer(450, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            linearlayout_sort.setVisibility(View.GONE);
                        }
                    }.start();

                }
            }
        });


        btn_relevance_Sort_Filter_Activity.setSelected(true);
        txtview_name_sort_Filter_Activity.setText(btn_relevance_Sort_Filter_Activity.getText());

        btn_relevance_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_relevance_Sort_Filter_Activity.setSelected(true);

                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);

                sortExtra = "";
                // close linearlayout
                clickSortItem();

                txtview_name_sort_Filter_Activity.setText(btn_relevance_Sort_Filter_Activity.getText());

            }
        });

        btn_likes_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_likes_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);

                //
                sortExtra = "likeAmount";
                // close linearlayout
                clickSortItem();

                txtview_name_sort_Filter_Activity.setText(btn_likes_Sort_Filter_Activity.getText());

            }
        });

        btn_rating_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_rating_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                //
                sortExtra = "ratingAmount";
                // close linearlayout
                clickSortItem();

                txtview_name_sort_Filter_Activity.setText(btn_rating_Sort_Filter_Activity.getText());

            }
        });

        btn_calories_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_calories_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);

                //
                sortExtra = "caloriesSort";
                // close linearlayout
                clickSortItem();

                txtview_name_sort_Filter_Activity.setText(btn_calories_Sort_Filter_Activity.getText());

            }
        });

        btn_preparationTime_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_preparationTime_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);

                //
                sortExtra = "preparationTimeSort";

                // close linearlayout
                clickSortItem();

                txtview_name_sort_Filter_Activity.setText(btn_preparationTime_Sort_Filter_Activity.getText());
            }
        });


    }

    public void clickSortItem(){

        // close linearlayout
        linearlayout_sort.startAnimation(animationHide);

        new CountDownTimer(450, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                linearlayout_sort.setVisibility(View.GONE);
            }
        }.start();

        button_Sort.setSelected(false);

        isClicked_linearlayout_sort =! isClicked_linearlayout_sort;

    }

    public void clickCategoryItem(){

        // close linearlayout
        linearlayout_category.startAnimation(animationHide);

        new CountDownTimer(450, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                linearlayout_category.setVisibility(View.GONE);
            }
        }.start();

        button_Category.setSelected(false);

        isClicked_linearlayout_Category =! isClicked_linearlayout_Category;

    }
    public void handlePartCategory(){


        // VIEW IS VISIBLE
        button_Category.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                isClicked_linearlayout_Category = !isClicked_linearlayout_Category;

                if (isClicked_linearlayout_Category){

                    button_Category.setSelected(true);

                    linearlayout_category.setVisibility(View.VISIBLE);
                    linearlayout_category.startAnimation(animationShow);
                }
                else{

                    button_Category.setSelected(false);

                    linearlayout_category.startAnimation(animationHide);

                    new CountDownTimer(500, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            linearlayout_category.setVisibility(View.GONE);
                        }
                    }.start();


                }
            }
        });


        cardView_starter_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!cardView_starter_category_Filter_Activity.isSelected()){

                    //
                    cardView_starter_category_Filter_Activity.setSelected(true);
                    txtView_starter_category_filter_activity.setSelected(true);

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "starter";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Starter");
                }
                else{

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }


            }
        });

        cardView_main_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_main_category_Filter_Activity.isSelected()){

                    cardView_main_category_Filter_Activity.setSelected(true);
                    txtView_main_category_filter_activity.setSelected(true);

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "main";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Main");
                }
                else{

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }

            }
        });

        cardView_dessert_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_dessert_category_Filter_Activity.isSelected()){

                    cardView_dessert_category_Filter_Activity.setSelected(true);
                    txtView_dessert_category_filter_activity.setSelected(true);

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "dessert";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Dessert");
                }
                else{

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }


            }
        });

        cardView_snack_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_snack_category_Filter_Activity.isSelected()){

                    cardView_snack_category_Filter_Activity.setSelected(true);
                    txtView_snack_category_filter_activity.setSelected(true);

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "snack";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Snack");
                }
                else{

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }


            }
        });

        cardView_breakfast_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_breakfast_category_Filter_Activity.isSelected()){


                    cardView_breakfast_category_Filter_Activity.setSelected(true);
                    txtView_breakfast_category_filter_activity.setSelected(true);

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "breakfast";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Breakfast");
                }
                else{

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }

            }
        });

        cardView_drink_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!cardView_drink_category_Filter_Activity.isSelected()){

                    cardView_drink_category_Filter_Activity.setSelected(true);
                    txtView_drink_category_filter_activity.setSelected(true);

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "cold drinks";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("Drink");
                }
                else{

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);

                    //
                    categoryExtra = "";

                    clickCategoryItem();

                    txtview_name_category_Filter_Activity.setText("");
                }


            }
        });

    }

    public void clickDietItem(){

        // close linearlayout
        linearlayout_diet.startAnimation(animationHide);

        new CountDownTimer(450, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                linearlayout_diet.setVisibility(View.GONE);
            }
        }.start();

        button_Diet.setSelected(false);

        isClicked_linearlayout_Diet =! isClicked_linearlayout_Diet;

    }
    public void handlePartDiet(){

        // VIEW IS VISIBLE
        button_Diet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                isClicked_linearlayout_Diet = !isClicked_linearlayout_Diet;

                if (isClicked_linearlayout_Diet){

                    button_Diet.setSelected(true);
                    linearlayout_diet.setVisibility(View.VISIBLE);
                    linearlayout_diet.startAnimation(animationShow);

                }
                else{

                    button_Diet.setSelected(false);

                    linearlayout_diet.startAnimation(animationHide);

                    new CountDownTimer(500, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            linearlayout_diet.setVisibility(View.GONE);
                        }
                    }.start();

                }

            }
        });

        cardView_meatless_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_meatless_diet_Filter_Activity.isSelected()){

                    cardView_meatless_diet_Filter_Activity.setSelected(true);
                    txtView_Meatless_diet_filter_activity.setSelected(true);

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "Meatless";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("Meatless");
                }
                else{

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("");
                }


            }
        });

        cardView_vegetarian_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_vegetarian_diet_Filter_Activity.isSelected()){

                    cardView_vegetarian_diet_Filter_Activity.setSelected(true);
                    txtView_Vegetarian_diet_filter_activity.setSelected(true);

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "vegetarian";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("Vegetarian");
                }
                else{

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("");

                }


            }
        });

        cardView_vegan_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_vegan_diet_Filter_Activity.isSelected()){

                    cardView_vegan_diet_Filter_Activity.setSelected(true);
                    txtView_Vegan_diet_filter_activity.setSelected(true);

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "vegan";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("Vegan");
                }
                else{

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("");
                }

            }
        });

        cardView_glutenFree_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_glutenFree_diet_Filter_Activity.isSelected()){

                    cardView_glutenFree_diet_Filter_Activity.setSelected(true);
                    txtView_GlutenFree_diet_filter_activity.setSelected(true);

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "gluten free";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("Gluten-free");
                }
                else{

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("");

                }


            }
        });

        cardView_sugarFree_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_sugarFree_diet_Filter_Activity.isSelected()){

                    cardView_sugarFree_diet_Filter_Activity.setSelected(true);
                    txtView_SugarFree_diet_filter_activity.setSelected(true);

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "lactose free";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("Sugar-free");
                }
                else{

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    //
                    dietExtra = "";

                    clickDietItem();

                    txtview_name_diet_Filter_Activity.setText("");
                }


            }
        });

        cardView_alcohol_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!cardView_alcohol_diet_Filter_Activity.isSelected()){

                    cardView_alcohol_diet_Filter_Activity.setSelected(true);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(true);

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);

                    clickDietItem();
                }
                else{

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);

                    clickDietItem();
                }
            }
        });
    }


    public void handlePartCuisine(){

        // VIEW IS VISIBLE
        button_Cuisine.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                isClicked_linearlayout_Cuisine = !isClicked_linearlayout_Cuisine;

                if(isClicked_linearlayout_Cuisine){

                    button_Cuisine.setSelected(true);
                    linearlayout_cuisine.setVisibility(View.VISIBLE);
                    linearlayout_cuisine.startAnimation(animationShow);

                }
                else{

                    button_Cuisine.setSelected(false);

                    linearlayout_cuisine.startAnimation(animationHide);

                    new CountDownTimer(500, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            linearlayout_cuisine.setVisibility(View.GONE);
                        }
                    }.start();

                }


            }
        });


        btn_Chinese_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Chinese_cuisine_Filter_Activity.isSelected()){

                    btn_Chinese_cuisine_Filter_Activity.setSelected(true);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "Chinese";

                    txtview_name_cuisine_Filter_Activity.setText("Chinese");

                }
                else{
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_Italian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Italian_cuisine_Filter_Activity.isSelected()){

                    btn_Italian_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "italian";

                    txtview_name_cuisine_Filter_Activity.setText("Italian");
                }
                else{

                    btn_Italian_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_European_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_European_cuisine_Filter_Activity.isSelected()){

                    btn_European_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "italian";

                    txtview_name_cuisine_Filter_Activity.setText("European");
                }
                else{

                    btn_European_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_Asian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Asian_cuisine_Filter_Activity.isSelected()){

                    btn_Asian_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "Asian";

                    txtview_name_cuisine_Filter_Activity.setText("Asian");
                }
                else{
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_American_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_American_cuisine_Filter_Activity.isSelected()){

                    btn_American_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "american";

                    txtview_name_cuisine_Filter_Activity.setText("American");
                }
                else{
                    btn_American_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Spanish_and_Portuguese_cuisine_Filter_Activity.isSelected()){

                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "spanish and portuguese";

                    txtview_name_cuisine_Filter_Activity.setText("Spanish and Portuguese");
                }
                else{
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_Indian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Indian_cuisine_Filter_Activity.isSelected()){

                    btn_Indian_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "indian";

                    txtview_name_cuisine_Filter_Activity.setText("Indian");
                }
                else{
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });

        btn_Middle_Eastern_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!btn_Middle_Eastern_cuisine_Filter_Activity.isSelected()){

                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(true);
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "middle eastern";

                    txtview_name_cuisine_Filter_Activity.setText("Middle Eastern");
                }
                else{
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);

                    //
                    cuisineExtra = "";

                    txtview_name_cuisine_Filter_Activity.setText("");
                }


            }
        });
    }


    public void handlePartOccasion(){

        // VIEW IS VISIBLE
        button_Occasion.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){

                    button_Occasion.setSelected(true);
                    linearlayout_occasion.setVisibility(View.VISIBLE);
                    linearlayout_occasion.startAnimation(animationShow);

                }
                else{

                    button_Occasion.setSelected(false);
                    linearlayout_occasion.startAnimation(animationHide);

                    new CountDownTimer(500, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            linearlayout_occasion.setVisibility(View.GONE);
                        }
                    }.start();

                }

            }
        });

        btn_WeeknightDinner_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_WeeknightDinner_Occasion_Filter_Activity.setSelected(true);

                    //occasionExtra.add(0, "weeknight dinner");
                    occasionExtra.add("weeknight dinner");
                }
                else{
                    btn_WeeknightDinner_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("weeknight dinner");
                }
            }
        });
        btn_Prepare_ahead_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Prepare_ahead_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("prepare ahead");
                }
                else{
                    btn_Prepare_ahead_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("prepare ahead");
                }
            }
        });
        btn_CrowdPleaser_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_CrowdPleaser_Occasion_Filter_Activity.setSelected(true);
                    occasionExtra.add("crowd pleaser");
                }
                else{
                    btn_CrowdPleaser_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("crowd pleaser");
                }
            }
        });
        btn_OnTheGo_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_OnTheGo_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("on the go");
                }
                else{
                    btn_OnTheGo_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("on the go");
                }
            }
        });
        btn_ComfortFood_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_ComfortFood_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("comfort food");
                }
                else{
                    btn_ComfortFood_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("comfort food");
                }
            }
        });
        btn_KidFriendly_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_KidFriendly_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("kid friendly");
                }
                else{
                    btn_KidFriendly_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("kid friendly");
                }
            }
        });
        btn_FingerFood_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_FingerFood_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("finger food");
                }
                else{
                    btn_FingerFood_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("finger food");
                }
            }
        });
        btn_Barbecue_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Barbecue_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("barbecue");
                }
                else{
                    btn_Barbecue_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("barbecue");
                }
            }
        });
        btn_Christmas_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Christmas_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("christmas");
                }
                else{
                    btn_Christmas_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("christmas");
                }
            }
        });
        btn_Easter_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Easter_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("easter");
                }
                else{
                    btn_Easter_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("easter");
                }
            }
        });
        btn_ValentinesDay_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_ValentinesDay_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("valentines day");
                }
                else{
                    btn_ValentinesDay_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("valentines day");
                }
            }
        });
        btn_Halloween_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Halloween_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("halloween");
                }
                else{
                    btn_Halloween_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("halloween");
                }
            }
        });
        btn_Octoberfest_Occasion_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Octoberfest_Occasion_Filter_Activity.setSelected(true);

                    occasionExtra.add("oktoberfest");
                }
                else{
                    btn_Octoberfest_Occasion_Filter_Activity.setSelected(false);

                    occasionExtra.remove("oktoberfest");
                }
            }
        });
    }

    public void setVisibilityGoneLinearLayout(){

        // VISIBILITY IS GONE
        linearlayout_sort.setVisibility(View.GONE);
        linearlayout_category.setVisibility(View.GONE);
        linearlayout_diet.setVisibility(View.GONE);
        linearlayout_cuisine.setVisibility(View.GONE);
        linearlayout_occasion.setVisibility(View.GONE);
    }



    public void findByIdByComments(){

        toolbar = findViewById(R.id.topAppBar_Filter_Activity);

        btn_Done_FilterRecipe = findViewById(R.id.btn_Done_FilterRecipe);

        button_Sort = findViewById(R.id.button_sort_Filter_Activity);
        button_Category = findViewById(R.id.button_category_Filter_Activity);
        button_Diet = findViewById(R.id.button_diet_Filter_Activity);
        button_Cuisine = findViewById(R.id.button_cuisine_Filter_Activity);
        button_Occasion = findViewById(R.id.button_Occasion_Filter_Activity);

        linearlayout_sort = findViewById(R.id.linearlayout_sort_Filter_Activity);
        linearlayout_category = findViewById(R.id.linearlayout_category_Filter_Activity);
        linearlayout_diet = findViewById(R.id.linearlayout_diet_Filter_Activity);
        linearlayout_cuisine = findViewById(R.id.linearlayout_cuisine_Filter_Activity);
        linearlayout_occasion = findViewById(R.id.linearlayout_Occasion_Filter_Activity);

        txtview_name_category_Filter_Activity = findViewById(R.id.txtview_name_category_Filter_Activity);
        txtview_name_diet_Filter_Activity = findViewById(R.id.txtview_name_diet_Filter_Activity);
        txtview_name_cuisine_Filter_Activity = findViewById(R.id.txtview_name_cuisine_Filter_Activity);
        txtview_name_occasion_Filter_Activity = findViewById(R.id.txtview_name_occasion_Filter_Activity);

        cardView_starter_category_Filter_Activity = findViewById(R.id.cardView_starter_category_Filter_Activity);
        cardView_main_category_Filter_Activity = findViewById(R.id.cardView_main_category_Filter_Activity);
        cardView_dessert_category_Filter_Activity = findViewById(R.id.cardView_dessert_category_Filter_Activity);
        cardView_snack_category_Filter_Activity = findViewById(R.id.cardView_snack_category_Filter_Activity);
        cardView_breakfast_category_Filter_Activity = findViewById(R.id.cardView_breakfast_category_Filter_Activity);
        cardView_drink_category_Filter_Activity = findViewById(R.id.cardView_drink_category_Filter_Activity);

        cardView_meatless_diet_Filter_Activity = findViewById(R.id.cardView_meatless_diet_Filter_Activity);
        cardView_vegetarian_diet_Filter_Activity = findViewById(R.id.cardView_vegetarian_diet_Filter_Activity);
        cardView_vegan_diet_Filter_Activity = findViewById(R.id.cardView_vegan_diet_Filter_Activity);
        cardView_glutenFree_diet_Filter_Activity = findViewById(R.id.cardView_glutenFree_diet_Filter_Activity);
        cardView_sugarFree_diet_Filter_Activity = findViewById(R.id.cardView_sugarFree_diet_Filter_Activity);
        cardView_alcohol_diet_Filter_Activity = findViewById(R.id.cardView_alcohol_diet_Filter_Activity);


        txtView_starter_category_filter_activity = findViewById(R.id.txtView_starter_category_filter_activity);
        txtView_main_category_filter_activity = findViewById(R.id.txtView_main_category_filter_activity);
        txtView_dessert_category_filter_activity = findViewById(R.id.txtView_dessert_category_filter_activity);
        txtView_snack_category_filter_activity = findViewById(R.id.txtView_snack_category_filter_activity);
        txtView_breakfast_category_filter_activity = findViewById(R.id.txtView_breakfast_category_filter_activity);
        txtView_drink_category_filter_activity = findViewById(R.id.txtView_drink_category_filter_activity);

        txtView_Meatless_diet_filter_activity = findViewById(R.id.txtView_Meatless_diet_filter_activity);
        txtView_Vegetarian_diet_filter_activity = findViewById(R.id.txtView_Vegetarian_diet_filter_activity);
        txtView_Vegan_diet_filter_activity = findViewById(R.id.txtView_Vegan_diet_filter_activity);
        txtView_GlutenFree_diet_filter_activity = findViewById(R.id.txtView_GlutenFree_diet_filter_activity);
        txtView_SugarFree_diet_filter_activity = findViewById(R.id.txtView_SugarFree_diet_filter_activity);
        txtView_AlcoholFree_diet_filter_activity = findViewById(R.id.txtView_AlcoholFree_diet_filter_activity);



        btn_relevance_Sort_Filter_Activity = findViewById(R.id.btn_relevance_Sort_Filter_Activity);
        btn_likes_Sort_Filter_Activity = findViewById(R.id.btn_likes_Sort_Filter_Activity);
        btn_rating_Sort_Filter_Activity = findViewById(R.id.btn_rating_Sort_Filter_Activity);
        btn_calories_Sort_Filter_Activity = findViewById(R.id.btn_calories_Sort_Filter_Activity);
        btn_preparationTime_Sort_Filter_Activity = findViewById(R.id.btn_preparationTime_Sort_Filter_Activity);


        btn_Chinese_cuisine_Filter_Activity = findViewById(R.id.btn_Chinese_cuisine_Filter_Activity);
        btn_Italian_cuisine_Filter_Activity = findViewById(R.id.btn_Italian_cuisine_Filter_Activity);
        btn_European_cuisine_Filter_Activity = findViewById(R.id.btn_European_cuisine_Filter_Activity);
        btn_Asian_cuisine_Filter_Activity = findViewById(R.id.btn_Asian_cuisine_Filter_Activity);
        btn_American_cuisine_Filter_Activity = findViewById(R.id.btn_American_cuisine_Filter_Activity);
        btn_Spanish_and_Portuguese_cuisine_Filter_Activity = findViewById(R.id.btn_Spanish_and_Portuguese_cuisine_Filter_Activity);
        btn_Indian_cuisine_Filter_Activity = findViewById(R.id.btn_Indian_cuisine_Filter_Activity);
        btn_Middle_Eastern_cuisine_Filter_Activity = findViewById(R.id.btn_Middle_Eastern_cuisine_Filter_Activity);



        btn_WeeknightDinner_Occasion_Filter_Activity = findViewById(R.id.btn_WeeknightDinner_Occasion_Filter_Activity);
        btn_Prepare_ahead_Occasion_Filter_Activity = findViewById(R.id.btn_Prepare_ahead_Occasion_Filter_Activity);
        btn_CrowdPleaser_Occasion_Filter_Activity = findViewById(R.id.btn_CrowdPleaser_Occasion_Filter_Activity);
        btn_OnTheGo_Occasion_Filter_Activity = findViewById(R.id.btn_OnTheGo_Occasion_Filter_Activity);
        btn_ComfortFood_Occasion_Filter_Activity = findViewById(R.id.btn_ComfortFood_Occasion_Filter_Activity);
        btn_KidFriendly_Occasion_Filter_Activity = findViewById(R.id.btn_KidFriendly_Occasion_Filter_Activity);
        btn_FingerFood_Occasion_Filter_Activity = findViewById(R.id.btn_FingerFood_Occasion_Filter_Activity);
        btn_Barbecue_Occasion_Filter_Activity = findViewById(R.id.btn_Barbecue_Occasion_Filter_Activity);
        btn_Christmas_Occasion_Filter_Activity = findViewById(R.id.btn_Christmas_Occasion_Filter_Activity);
        btn_Easter_Occasion_Filter_Activity = findViewById(R.id.btn_Easter_Occasion_Filter_Activity);
        btn_ValentinesDay_Occasion_Filter_Activity = findViewById(R.id.btn_ValentinesDay_Occasion_Filter_Activity);
        btn_Halloween_Occasion_Filter_Activity = findViewById(R.id.btn_Halloween_Occasion_Filter_Activity);
        btn_Octoberfest_Occasion_Filter_Activity = findViewById(R.id.btn_Octoberfest_Occasion_Filter_Activity);



        txtview_name_sort_Filter_Activity = findViewById(R.id.txtview_name_sort_Filter_Activity);

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