package com.example.kitchenstories.View;

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
import com.example.kitchenstories.aOthersClass.DotsIndicatorDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    private NestedScrollView nestedScrollView;
    private MediaController mediaController;

    private RecyclerView recyclerView_part1;
    private ArrayList<Recipe> mData_part1;

    private RecyclerView recyclerView_part4;
    private ArrayList<Recipe> mData_part4;

    private RecyclerView recyclerView_part7;
    private ArrayList<Recipe> mData_part7;



    // CREATE A DOT INDICATOR FOR RECYCLERVIEW
//    int radius;
//    int dotsHeight;
//    int colorInactive;
//    int colorActive;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        transparentStatusAndNavigation();




        // FIND VIEW BY ID
        toolbar = findViewById(R.id.toolbar_today_activity);

        videoView_part2 = findViewById(R.id.videoView_part2_today_activity);

        nestedScrollView = findViewById(R.id.nestedScrollView_today_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        recyclerView_part1 = findViewById(R.id.recyclerView_part1_today_activity);

        recyclerView_part4 = findViewById(R.id.recyclerView_part4_today_activity);

        videoView_part6 = findViewById(R.id.videoView_part6_today_activity);

        recyclerView_part7 = findViewById(R.id.recyclerView_part7_today_activity);



        //set up notitle
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        // BOTTOM NAVIGATION
        bottomNavigationView.setSelectedItemId(R.id.today);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.today:
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0,0);
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


        // PART 1: RecyclerView_part1
        recyclerView_part1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        init_mData_part1();
        RecyclerViewAdapter_Medium adapter_medium = new RecyclerViewAdapter_Medium(MainActivity.this,mData_part1);
        recyclerView_part1.setAdapter(adapter_medium);






        // PART2: VIDEO VIEW
        String videoPath = "android.resource://" + getPackageName() +"/"+R.raw.mtp_video;
        Uri uri = Uri.parse(videoPath);
        videoView_part2.setVideoURI(uri);
        videoView_part2.requestFocus();

        // SET MUTE TO VIDEO VIEW
        videoView_part2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f,0f);
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
        String videoPath_part6= "android.resource://" + getPackageName() +"/"+R.raw.chillies_mv;
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




        // videoView in localVisible of NestedScroll
        final Rect scrollBounds = new Rect();
        nestedScrollView.getHitRect(scrollBounds);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                // VIDEOVIEW PART 2 is autoplayed
                if(videoView_part2 != null){

                    if(videoView_part2.getLocalVisibleRect(scrollBounds)){

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
                    }
                    else{

                        videoView_part2.pause();
                        //Toast.makeText(MainActivity.this, "VIDEOVIEW NO APPEAR", Toast.LENGTH_SHORT).show();
                    }

                }

                 //VIDEOVIEW PART 6 is autoplayed
                if(videoView_part6 != null){

                    if(videoView_part6.getLocalVisibleRect(scrollBounds)){

                        new CountDownTimer(2000,1000){

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoView_part6.start();
                            }
                        }.start();
                    }
                    else{
                        videoView_part6.pause();
                    }
                }

            }
        });



        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int colorInactive = ContextCompat.getColor(MainActivity.this, R.color.Gray500);
        final int colorActive = ContextCompat.getColor(MainActivity.this, R.color.black);



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

    }



    private void init_mData_part1(){

        mData_part1 = new ArrayList<>();

        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part1.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part4(){

        mData_part4 = new ArrayList<>();

        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part4.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

    }

    private void init_mData_part7(){

        mData_part7 = new ArrayList<>();

        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));
        mData_part7.add(new Recipe(R.drawable.ic_launcher_background, "Make easy Neapolitan-style pizza with lisa", R.drawable.ic_launcher_background, "Thang Tran", "Kitchen Stories"));

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