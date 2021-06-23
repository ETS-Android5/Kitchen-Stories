package com.example.kitchenstories.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kitchenstories.R;

public class Filter_Recipe extends AppCompatActivity {

    private Toolbar toolbar;

    private Button btn_Done_FilterRecipe;

    private Button button_Sort;
    private Button button_Category;
    private Button button_Diet;
    private Button button_Cuisine;
    private Button button_MainIngredients;
    private Button button_Occasion;

    private LinearLayout linearlayout_sort;
    private LinearLayout linearlayout_category;
    private LinearLayout linearlayout_diet;
    private LinearLayout linearlayout_cuisine;
    private LinearLayout linearlayout_mainIngredients;
    private LinearLayout linearlayout_occasion;


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
    private Button btn_commented_Sort_Filter_Activity;
    private Button btn_calories_Sort_Filter_Activity;
    private Button btn_preparationTime_Sort_Filter_Activity;
    private Button btn_releaseDate_Sort_Filter_Activity;

    private Button btn_Chinese_cuisine_Filter_Activity;
    private Button btn_Italian_cuisine_Filter_Activity;
    private Button btn_European_cuisine_Filter_Activity;
    private Button btn_Asian_cuisine_Filter_Activity;
    private Button btn_American_cuisine_Filter_Activity;
    private Button btn_Spanish_and_Portuguese_cuisine_Filter_Activity;
    private Button btn_Indian_cuisine_Filter_Activity;
    private Button btn_Middle_Eastern_cuisine_Filter_Activity;

    private Button btn_Vegetables_MainIngredients_Filter_Activity;
    private Button btn_Chicken_MainIngredients_Filter_Activity;
    private Button btn_Pasta_MainIngredients_Filter_Activity;
    private Button btn_Beef_MainIngredients_Filter_Activity;
    private Button btn_Seafood_MainIngredients_Filter_Activity;
    private Button btn_Pork_MainIngredients_Filter_Activity;
    private Button btn_Fruit_MainIngredients_Filter_Activity;
    private Button btn_Cheese_MainIngredients_Filter_Activity;

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

    private TextView txtview_name_sort_Filter_Activity;



    private Animation animationShow;
    private Animation animationHide;

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



        // PART: SORT
        // VISIBILITY LINEAR LAYOUT

        btn_Done_FilterRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);


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

                txtview_name_sort_Filter_Activity.setText(btn_relevance_Sort_Filter_Activity.getText());

            }
        });

        btn_likes_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_likes_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);

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

                txtview_name_sort_Filter_Activity.setText(btn_likes_Sort_Filter_Activity.getText());

            }
        });

        btn_rating_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_rating_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);

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

                txtview_name_sort_Filter_Activity.setText(btn_rating_Sort_Filter_Activity.getText());

            }
        });

        btn_commented_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_commented_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);

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

                txtview_name_sort_Filter_Activity.setText(btn_commented_Sort_Filter_Activity.getText());
            }
        });

        btn_calories_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_calories_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);

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
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_releaseDate_Sort_Filter_Activity.setSelected(false);

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

                txtview_name_sort_Filter_Activity.setText(btn_preparationTime_Sort_Filter_Activity.getText());
            }
        });

        btn_releaseDate_Sort_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_releaseDate_Sort_Filter_Activity.setSelected(true);

                btn_relevance_Sort_Filter_Activity.setSelected(false);
                btn_likes_Sort_Filter_Activity.setSelected(false);
                btn_rating_Sort_Filter_Activity.setSelected(false);
                btn_commented_Sort_Filter_Activity.setSelected(false);
                btn_calories_Sort_Filter_Activity.setSelected(false);
                btn_preparationTime_Sort_Filter_Activity.setSelected(false);

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

                txtview_name_sort_Filter_Activity.setText(btn_releaseDate_Sort_Filter_Activity.getText());

            }
        });













        // PART: CATEGORY
        // VIEW IS VISIBLE
        button_Category.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if (isClicked){

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

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_starter_category_Filter_Activity.setSelected(true);
                    txtView_starter_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_starter_category_Filter_Activity.setSelected(false);
                    txtView_starter_category_filter_activity.setSelected(false);
                }
            }
        });

        cardView_main_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_main_category_Filter_Activity.setSelected(true);
                    txtView_main_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_main_category_Filter_Activity.setSelected(false);
                    txtView_main_category_filter_activity.setSelected(false);
                }
            }
        });

        cardView_dessert_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_dessert_category_Filter_Activity.setSelected(true);
                    txtView_dessert_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_dessert_category_Filter_Activity.setSelected(false);
                    txtView_dessert_category_filter_activity.setSelected(false);
                }
            }
        });

        cardView_snack_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_snack_category_Filter_Activity.setSelected(true);
                    txtView_snack_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_snack_category_Filter_Activity.setSelected(false);
                    txtView_snack_category_filter_activity.setSelected(false);
                }
            }
        });

        cardView_breakfast_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_breakfast_category_Filter_Activity.setSelected(true);
                    txtView_breakfast_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_breakfast_category_Filter_Activity.setSelected(false);
                    txtView_breakfast_category_filter_activity.setSelected(false);
                }
            }
        });

        cardView_drink_category_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_drink_category_Filter_Activity.setSelected(true);
                    txtView_drink_category_filter_activity.setSelected(true);

                }
                else{

                    cardView_drink_category_Filter_Activity.setSelected(false);
                    txtView_drink_category_filter_activity.setSelected(false);
                }
            }
        });


















        // PART: DIET
        // VIEW IS VISIBLE
        button_Diet.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if (isClicked){

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

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_meatless_diet_Filter_Activity.setSelected(true);
                    txtView_Meatless_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_meatless_diet_Filter_Activity.setSelected(false);
                    txtView_Meatless_diet_filter_activity.setSelected(false);
                }
            }
        });

        cardView_vegetarian_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_vegetarian_diet_Filter_Activity.setSelected(true);
                    txtView_Vegetarian_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_vegetarian_diet_Filter_Activity.setSelected(false);
                    txtView_Vegetarian_diet_filter_activity.setSelected(false);
                }
            }
        });

        cardView_vegan_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_vegan_diet_Filter_Activity.setSelected(true);
                    txtView_Vegan_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_vegan_diet_Filter_Activity.setSelected(false);
                    txtView_Vegan_diet_filter_activity.setSelected(false);
                }
            }
        });

        cardView_glutenFree_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_glutenFree_diet_Filter_Activity.setSelected(true);
                    txtView_GlutenFree_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_glutenFree_diet_Filter_Activity.setSelected(false);
                    txtView_GlutenFree_diet_filter_activity.setSelected(false);
                }
            }
        });


        cardView_sugarFree_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_sugarFree_diet_Filter_Activity.setSelected(true);
                    txtView_SugarFree_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_sugarFree_diet_Filter_Activity.setSelected(false);
                    txtView_SugarFree_diet_filter_activity.setSelected(false);
                }
            }
        });

        cardView_alcohol_diet_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

                    cardView_alcohol_diet_Filter_Activity.setSelected(true);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(true);

                }
                else{

                    cardView_alcohol_diet_Filter_Activity.setSelected(false);
                    txtView_AlcoholFree_diet_filter_activity.setSelected(false);
                }
            }
        });





















        // PART: CUISINE
        // VIEW IS VISIBLE
        button_Cuisine.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if(isClicked){

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

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Chinese_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Chinese_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Italian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Italian_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Italian_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_European_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_European_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_European_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Asian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Asian_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Asian_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_American_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_American_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_American_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Spanish_and_Portuguese_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Indian_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Indian_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Indian_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Middle_Eastern_cuisine_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Middle_Eastern_cuisine_Filter_Activity.setSelected(false);
                }
            }
        });






        // PART: DIET
        // VIEW IS VISIBLE
        button_MainIngredients.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;
                if (isClicked){

                    button_MainIngredients.setSelected(true);
                    linearlayout_mainIngredients.setVisibility(View.VISIBLE);
                    linearlayout_mainIngredients.startAnimation(animationShow);

                }
                else{

                    button_MainIngredients.setSelected(false);

                    linearlayout_mainIngredients.startAnimation(animationHide);

                    new CountDownTimer(500, 1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            linearlayout_mainIngredients.setVisibility(View.GONE);
                        }
                    }.start();

                }

            }
        });


        btn_Vegetables_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Vegetables_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Vegetables_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Chicken_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Chicken_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Chicken_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Pasta_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Pasta_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Pasta_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Beef_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Beef_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Beef_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Seafood_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Seafood_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Seafood_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Pork_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Pork_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Pork_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Fruit_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Fruit_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Fruit_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });

        btn_Cheese_MainIngredients_Filter_Activity.setOnClickListener(new View.OnClickListener() {

            boolean isClicked = false;
            @Override
            public void onClick(View v) {

                isClicked = !isClicked;

                if(isClicked){
                    btn_Cheese_MainIngredients_Filter_Activity.setSelected(true);
                }
                else{
                    btn_Cheese_MainIngredients_Filter_Activity.setSelected(false);
                }
            }
        });





        // PART: OCCASION
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
                }
                else{
                    btn_WeeknightDinner_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Prepare_ahead_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_CrowdPleaser_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_OnTheGo_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_ComfortFood_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_KidFriendly_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_FingerFood_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Barbecue_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Christmas_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Easter_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_ValentinesDay_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Halloween_Occasion_Filter_Activity.setSelected(false);
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
                }
                else{
                    btn_Octoberfest_Occasion_Filter_Activity.setSelected(false);
                }
            }
        });




        // the end of Create
    }

    public void findByIdByComments(){

        toolbar = findViewById(R.id.topAppBar_Filter_Activity);

        btn_Done_FilterRecipe = findViewById(R.id.btn_Done_FilterRecipe);

        button_Sort = findViewById(R.id.button_sort_Filter_Activity);
        button_Category = findViewById(R.id.button_category_Filter_Activity);
        button_Diet = findViewById(R.id.button_diet_Filter_Activity);
        button_Cuisine = findViewById(R.id.button_cuisine_Filter_Activity);
        button_MainIngredients = findViewById(R.id.button__MainIngredients_Filter_Activity);
        button_Occasion = findViewById(R.id.button_Occasion_Filter_Activity);

        linearlayout_sort = findViewById(R.id.linearlayout_sort_Filter_Activity);
        linearlayout_category = findViewById(R.id.linearlayout_category_Filter_Activity);
        linearlayout_diet = findViewById(R.id.linearlayout_diet_Filter_Activity);
        linearlayout_cuisine = findViewById(R.id.linearlayout_cuisine_Filter_Activity);
        linearlayout_mainIngredients = findViewById(R.id.linearlayout_MainIngredients_Filter_Activity);
        linearlayout_occasion = findViewById(R.id.linearlayout_Occasion_Filter_Activity);


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
        btn_commented_Sort_Filter_Activity = findViewById(R.id.btn_commented_Sort_Filter_Activity);
        btn_calories_Sort_Filter_Activity = findViewById(R.id.btn_calories_Sort_Filter_Activity);
        btn_preparationTime_Sort_Filter_Activity = findViewById(R.id.btn_preparationTime_Sort_Filter_Activity);
        btn_releaseDate_Sort_Filter_Activity = findViewById(R.id.btn_releaseDate_Sort_Filter_Activity);


        btn_Chinese_cuisine_Filter_Activity = findViewById(R.id.btn_Chinese_cuisine_Filter_Activity);
        btn_Italian_cuisine_Filter_Activity = findViewById(R.id.btn_Italian_cuisine_Filter_Activity);
        btn_European_cuisine_Filter_Activity = findViewById(R.id.btn_European_cuisine_Filter_Activity);
        btn_Asian_cuisine_Filter_Activity = findViewById(R.id.btn_Asian_cuisine_Filter_Activity);
        btn_American_cuisine_Filter_Activity = findViewById(R.id.btn_American_cuisine_Filter_Activity);
        btn_Spanish_and_Portuguese_cuisine_Filter_Activity = findViewById(R.id.btn_Spanish_and_Portuguese_cuisine_Filter_Activity);
        btn_Indian_cuisine_Filter_Activity = findViewById(R.id.btn_Indian_cuisine_Filter_Activity);
        btn_Middle_Eastern_cuisine_Filter_Activity = findViewById(R.id.btn_Middle_Eastern_cuisine_Filter_Activity);


        btn_Vegetables_MainIngredients_Filter_Activity = findViewById(R.id.btn_Vegetables_MainIngredients_Filter_Activity);
        btn_Chicken_MainIngredients_Filter_Activity = findViewById(R.id.btn_Chicken_MainIngredients_Filter_Activity);
        btn_Pasta_MainIngredients_Filter_Activity = findViewById(R.id.btn_Pasta_MainIngredients_Filter_Activity);
        btn_Beef_MainIngredients_Filter_Activity = findViewById(R.id.btn_Beef_MainIngredients_Filter_Activity);
        btn_Seafood_MainIngredients_Filter_Activity = findViewById(R.id.btn_Seafood_MainIngredients_Filter_Activity);
        btn_Pork_MainIngredients_Filter_Activity = findViewById(R.id.btn_Pork_MainIngredients_Filter_Activity);
        btn_Fruit_MainIngredients_Filter_Activity = findViewById(R.id.btn_Fruit_MainIngredients_Filter_Activity);
        btn_Cheese_MainIngredients_Filter_Activity = findViewById(R.id.btn_Cheese_MainIngredients_Filter_Activity);


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


    public void setVisibilityGoneLinearLayout(){

        // VISIBILITY IS GONE
        linearlayout_sort.setVisibility(View.GONE);
        linearlayout_category.setVisibility(View.GONE);
        linearlayout_diet.setVisibility(View.GONE);
        linearlayout_cuisine.setVisibility(View.GONE);
        linearlayout_mainIngredients.setVisibility(View.GONE);
        linearlayout_occasion.setVisibility(View.GONE);
    }


    public void clearDataInFilterActivity(){


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