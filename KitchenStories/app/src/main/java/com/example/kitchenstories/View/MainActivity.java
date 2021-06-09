package com.example.kitchenstories.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_LargeItem;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_Medium;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_part13;
import com.example.kitchenstories.aOthersClass.DotsIndicatorDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


//<color name="colorPrimary">#6200EE</color>
//<color name="colorPrimaryDark">#3700B3</color>
//<color name="colorAccent">#03DAC5</color>

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    private VideoView videoView_part2;
    private VideoView videoView_part6;
    private VideoView videoView_part8;

    private NestedScrollView nestedScrollView;
    private MediaController mediaController;

    private RecyclerView recyclerView_part1;
    private ArrayList<Recipe> mData_part1;

    private RecyclerView recyclerView_part4;
    private ArrayList<Recipe> mData_part4;

    private RecyclerView recyclerView_part7;
    private ArrayList<Recipe> mData_part7;

    private RecyclerView recyclerView_part9;
    private ArrayList<Recipe> mData_part9;

    private RecyclerView recyclerView_part10;
    private ArrayList<Recipe> mData_part10;

    private RecyclerView recyclerView_part11;
    private ArrayList<Recipe> mData_part11;

    private RecyclerView recyclerView_part12;
    private ArrayList<Recipe> mData_part12;

    private RecyclerView recyclerView_part13;
    private ArrayList<Recipe> mData_part13;

    // CREATE A DOT INDICATOR FOR RECYCLERVIEW
//    int radius;
//    int dotsHeight;
//    int colorInactive;
//    int colorActive;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        //transparentStatusAndNavigation();

        //TransparentStatusAndNavigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Transparent status bar
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Transparent navigation bar
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.Gray50));
        }

//        String name_cooking_recipe ="name4";
//        String url_image_CookingRecipe ="https://firebasestorage.googleapis.com/v0/b/loginapp-319bc.appspot.com/o/Recipe%2FRecipe1%2Fmain.jpeg?alt=media&token=4c033c30-a64e-49da-b7aa-57911098e53d";
//        int image_CookingRecipe = 4;
//
//        int image_author = 4;
//        String name_author = "name_author4";
//        String name_authorGroup ="name_authorGroup4";
//
//        String difficulty_Level_Recipe ="difficulty_Level_Recipe4";
//        String utensils = "utensils4";
//
//        ArrayList<String> periodCooking = new ArrayList<>();
//        periodCooking.add("25 mins.");
//        periodCooking.add("0 min.");
//        periodCooking.add("0 min.");
//
//        ArrayList<String> nutritionPerServing = new ArrayList<>();
//        nutritionPerServing.add("650");
//        nutritionPerServing.add("20 g");
//        nutritionPerServing.add("19 g");
//        nutritionPerServing.add("101 g");
//
//        ArrayList<String> tags = new ArrayList<>();
//        tags.add("main");
//        tags.add("lactose free");
//        tags.add("weeknight dinner");
//        tags.add("italian");
//        tags.add("meatless");
//        tags.add("pasta");
//        tags.add("asian");
//
//
//        ArrayList<String> ingredients = new ArrayList<>();
//        ingredients.add("cherry tomatoes");
//        ingredients.add("white miso paste");
//        ingredients.add("weeknight dinner");
//        ingredients.add("linguine");
//        ingredients.add("garlic");
//        ingredients.add("scallions");
//        ingredients.add("chili");
//        ingredients.add("parsley");
//        ingredients.add("water");
//        ingredients.add("toasted sesame oil");
//        ingredients.add("panko breadcrumbs");
//        ingredients.add("olive oil");
//        ingredients.add("honey");
//        ingredients.add("anchovies");
//        ingredients.add("unsalted butter");
//
//        ArrayList<String> amountOfIngredients = new ArrayList<>();
//        amountOfIngredients.add("350 g");
//        amountOfIngredients.add("2 tbsp");
//        amountOfIngredients.add("200 g");
//        amountOfIngredients.add("3 cloves");
//        amountOfIngredients.add("2");
//        amountOfIngredients.add("1");
//        amountOfIngredients.add("5 g");
//        amountOfIngredients.add("4 tbsp");
//        amountOfIngredients.add("1½ tsp");
//        amountOfIngredients.add("5 tbsp");
//        amountOfIngredients.add("2 tsp");
//        amountOfIngredients.add("1½ tsp");
//        amountOfIngredients.add("2 tsp");
//        amountOfIngredients.add("2");
//        amountOfIngredients.add("1 tbsp");
//
//
//        Recipe recipe4 = new Recipe(image_CookingRecipe,
//                name_cooking_recipe,
//                url_image_CookingRecipe,
//                image_author,
//                name_author,
//                name_authorGroup,
//                difficulty_Level_Recipe,
//                periodCooking,
//                ingredients,
//                amountOfIngredients,
//                utensils,
//                nutritionPerServing,
//                tags);
//
//        firebaseFirestore.collection("Recipe").document("Recipe6")
//                .set(recipe4);

//        Map<String,String> map = new HashMap<>();
//
//        map.put("hello1", "hello");
//        map.put("hello2", "hello");
//        map.put("hello3", "hello");

//        String step = "3/4";
//        String url_image = "url_image3";
//        String ingredientsForPerStep ="200 g linguine1½ tsp olive oil2 anchovies2 tsp honey";
//        String utensilsForPerStep ="";
//        String scriptForDescription ="Cook pasta according to package instructions until al dente. While the pasta is cooking, make the sauce: Add olive oil to a frying pan over medium heat. Once heated, add anchovies, sliced chili, garlic and fry until anchovies have 'dissolved', approx. 2 min. Add whole cherry tomatoes and the honey, and increase heat to high. Shake the pan now and then and let tomatoes wrinkle up and begin to char, approx. 4 min.";
//
//        StepsForRecipe stepsForRecipe = new StepsForRecipe(step, url_image,ingredientsForPerStep,utensilsForPerStep,scriptForDescription);
//
//        firebaseFirestore.collection("Recipe").document("Recipe6")
//                .collection("Steps").document("Step3").set(stepsForRecipe);



        // FIND VIEW BY ID
        //toolbar = findViewById(R.id.toolbar_today_activity);

        videoView_part2 = findViewById(R.id.videoView_part2_today_activity);

        nestedScrollView = findViewById(R.id.nestedScrollView_today_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        recyclerView_part1 = findViewById(R.id.recyclerView_part1_today_activity);

        recyclerView_part4 = findViewById(R.id.recyclerView_part4_today_activity);

        videoView_part6 = findViewById(R.id.videoView_part6_today_activity);

        recyclerView_part7 = findViewById(R.id.recyclerView_part7_today_activity);

        videoView_part8 = findViewById(R.id.videoView_part8_today_activity);

        recyclerView_part9 = findViewById(R.id.recyclerView_part9_today_activity);

        recyclerView_part10 = findViewById(R.id.recyclerView_part10_today_activity);

        recyclerView_part11 = findViewById(R.id.recyclerView_part11_today_activity);

        recyclerView_part12 = findViewById(R.id.recyclerView_part12_today_activity);

        recyclerView_part13 = findViewById(R.id.recyclerView_part13_today_activity);


        //set up notitle
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // BOTTOM NAVIGATION
        bottomNavigationView.setSelectedItemId(R.id.today);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.today:
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), Create.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.shopping:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });


        // PART2: VIDEO VIEW
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.mtp_video;
        Uri uri = Uri.parse(videoPath);
        videoView_part2.setVideoURI(uri);
        videoView_part2.requestFocus();

        // SET MUTE TO VIDEO VIEW
        videoView_part2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f, 0f);
                mp.setLooping(true);


                // CENTER CROP VIDEO VIEW
                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                float screenRatio = videoView_part2.getWidth() / (float)
                        videoView_part2.getHeight();
                float scaleX = videoRatio / screenRatio;
                if (scaleX >= 1f) {
                    videoView_part2.setScaleX(scaleX);
                } else {
                    videoView_part2.setScaleY(1f / scaleX);
                }

            }
        });


        // PART 6: VIDEOVIEW
        String videoPath_part6 = "android.resource://" + getPackageName() + "/" + R.raw.chillies_mv;
        Uri uri_part6 = Uri.parse(videoPath_part6);
        videoView_part6.setVideoURI(uri_part6);
        videoView_part6.requestFocus();

        // SET MUTE TO VIDEO VIEW
        videoView_part6.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f, 0f);
                mp.setLooping(true);

            }
        });


        // PART8: VIDEOVIEW
        String videoPath_part8 = "android.resource://" + getPackageName() + "/" + R.raw.chillies_mv;
        Uri uri_part8 = Uri.parse(videoPath_part8);
        videoView_part8.setVideoURI(uri_part8);
        videoView_part8.requestFocus();

        videoView_part8.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setVolume(0f, 0f);
                mp.setLooping(true);
            }
        });


        // videoView in localVisible of NestedScroll
        final Rect scrollBounds = new Rect();
        nestedScrollView.getHitRect(scrollBounds);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                // VIDEOVIEW PART 2 is autoplayed
                if (videoView_part2 != null) {

                    if (videoView_part2.getLocalVisibleRect(scrollBounds)) {

                        //Toast.makeText(MainActivity.this, "VIDEOVIEW APPEAR FULLY", Toast.LENGTH_SHORT).show();

                        // count down timer 2 seconds
                        new CountDownTimer(2000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {

                                videoView_part2.start();

//                                mediaController = new MediaController(MainActivity.this);
//                                videoView.setMediaController(mediaController);
//                                mediaController.setAnchorView(videoView);

                            }
                        }.start();
                    } else {

                        videoView_part2.pause();
                        //Toast.makeText(MainActivity.this, "VIDEOVIEW NO APPEAR", Toast.LENGTH_SHORT).show();
                    }

                }

                //VIDEOVIEW PART 6 is autoplayed
                if (videoView_part6 != null) {

                    if (videoView_part6.getLocalVisibleRect(scrollBounds)) {

                        new CountDownTimer(2000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoView_part6.start();
                            }
                        }.start();
                    } else {
                        videoView_part6.pause();
                    }
                }

                // VIDEOVIEW PART 8 is autoplayed
                if (videoView_part8 != null) {

                    if (videoView_part8.getLocalVisibleRect(scrollBounds)) {

                        new CountDownTimer(2000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoView_part8.start();
                            }
                        }.start();
                    } else {
                        videoView_part8.pause();
                    }
                }


            }
        });


        // RECYCLERVIEW

        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int colorInactive = ContextCompat.getColor(MainActivity.this, R.color.Gray500);
        final int colorActive = ContextCompat.getColor(MainActivity.this, R.color.black);


        // PART 1: RecyclerView_part1
        recyclerView_part1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part1();
        RecyclerViewAdapter_Medium adapter_medium = new RecyclerViewAdapter_Medium(MainActivity.this, mData_part1);
        recyclerView_part1.setAdapter(adapter_medium);


        // PART 4: RecyclerView
        recyclerView_part4.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part4();
        RecyclerViewAdapter_LargeItem adapter_largeItem = new RecyclerViewAdapter_LargeItem(MainActivity.this, mData_part4);
        recyclerView_part4.setAdapter(adapter_largeItem);

        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part4.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part4);


        // PART 7: RecyclerView
        recyclerView_part7.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part7();
        RecyclerViewAdapter_LargeItem adapter_largeItem_part7 = new RecyclerViewAdapter_LargeItem(MainActivity.this, mData_part7);
        recyclerView_part7.setAdapter(adapter_largeItem_part7);

        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part7.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part7);


        // PART 9: RecylerView
        recyclerView_part9.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part9();
        RecyclerViewAdapter_Medium adapter_part9 = new RecyclerViewAdapter_Medium(this, mData_part9);
        recyclerView_part9.setAdapter(adapter_part9);


        // PART 10: Recyclerview
        recyclerView_part10.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part10();
        RecyclerViewAdapter_LargeItem adapter_part10 = new RecyclerViewAdapter_LargeItem(this, mData_part10);
        recyclerView_part10.setAdapter(adapter_part10);

        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part10.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part10);


        // PART 11: RecylerView
        recyclerView_part11.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part11();
        RecyclerViewAdapter_Medium adapter_part11 = new RecyclerViewAdapter_Medium(this, mData_part11);
        recyclerView_part11.setAdapter(adapter_part11);


        // PART 12: RecylerView
        recyclerView_part12.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part12();
        RecyclerViewAdapter_Medium adapter_part12 = new RecyclerViewAdapter_Medium(this, mData_part12);
        recyclerView_part12.setAdapter(adapter_part12);


        // PART 13: RecyclerView
        recyclerView_part13.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part13();
        RecyclerViewAdapter_part13 adapter_part13 = new RecyclerViewAdapter_part13(this, mData_part13);
        recyclerView_part13.setAdapter(adapter_part13);


    }


    private void init_mData_part1() {

        mData_part1 = new ArrayList<>();

        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part4() {

        mData_part4 = new ArrayList<>();

        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part7() {

        mData_part7 = new ArrayList<>();

        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part9() {

        mData_part9 = new ArrayList<>();

        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part9.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part10() {

        mData_part10 = new ArrayList<>();

        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part10.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part11() {

        mData_part11 = new ArrayList<>();

        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part11.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part12() {

        mData_part12 = new ArrayList<>();

        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part12.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part13() {

        mData_part13 = new ArrayList<>();

        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "Strawberry Season"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "Breakfast"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "BBQ"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "Asparagus"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "Vegan"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "Recipes for Spring"));
        mData_part13.add(new Recipe(R.drawable.ic_launcher_background, "One-Pot"));

    }

    // Transparent Status Bar part 1
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

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    // Transparent Status Bar part 2
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