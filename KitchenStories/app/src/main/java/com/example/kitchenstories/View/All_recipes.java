package com.example.kitchenstories.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Fragment.fm_recipe_tab_1;
import com.example.kitchenstories.View.Fragment.fm_recipe_tab_2;
import com.example.kitchenstories.ViewModel.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class All_recipes extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView bottomNavigationView;

    private Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);

        //
        transparentStatusAndNavigation();

        Window window = All_recipes.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(All_recipes.this, R.color.Gray50));



        // FIND VIEW BY ID
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.topAppBar_All_Recipes_Activity);


        //
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add Fragment here
        viewPagerAdapter.AddFragment(new fm_recipe_tab_1(), "Kitchen Stories");
        viewPagerAdapter.AddFragment(new fm_recipe_tab_2(), "Community");

        // View
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);




        // Navigation bottom
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


        // Navigation Icon is Clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Toast.makeText(getBaseContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });



        // Filter Icon is clicked
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.filter_all_recipe_activity:
                        openFilterRecipe();
                        return true;

                }
                return false;
            }
        });







    }


    public void openFilterRecipe(){

        Intent intent = new Intent(this, Filter_Recipe.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_null);
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