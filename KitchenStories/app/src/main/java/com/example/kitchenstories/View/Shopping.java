package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitchenstories.Model.DBHelper;
import com.example.kitchenstories.Model.Shopping.RecipeForShopping;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.profile.Profile;
import com.example.kitchenstories.ViewModel.ShoppingActivity.ShoppingAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Shopping extends AppCompatActivity {

    private RecyclerView rcvShopping;
    private DBHelper dbHelper;
    List<RecipeForShopping> recipeForShoppingList ;

    TextView txt_ShoppingList;
    ImageView image1_ShoppingActivity;
    View image2_ShoppingActivity;
    TextView tv1_ShoppingActivity;
    TextView tv2_ShoppingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        txt_ShoppingList = findViewById(R.id.txt_ShoppingList);
        image1_ShoppingActivity = findViewById(R.id.image1_ShoppingActivity);
        image2_ShoppingActivity = findViewById(R.id.image2_ShoppingActivity);
        tv1_ShoppingActivity = findViewById(R.id.tv1_ShoppingActivity);
        tv2_ShoppingActivity = findViewById(R.id.tv2_ShoppingActivity);

        image1_ShoppingActivity.setVisibility(View.GONE);
        image2_ShoppingActivity.setVisibility(View.GONE);
        tv1_ShoppingActivity.setVisibility(View.GONE);
        tv2_ShoppingActivity.setVisibility(View.GONE);

        dbHelper = new DBHelper(this);
        LoadDataRcv();
        //
        transparentStatusAndNavigation();


        //
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.shopping);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.today:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), Create.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.shopping:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;
                }

                return false;
            }
        });
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

    private void getShoppingList(){
        recipeForShoppingList = dbHelper.getListRecipeForShopping();
    }
    private void LoadDataRcv(){
        rcvShopping = findViewById(R.id.rcv_shopping);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvShopping.setLayoutManager(linearLayoutManager);

        recipeForShoppingList = new ArrayList<>();
        getShoppingList();

        if (recipeForShoppingList == null){

            image1_ShoppingActivity.setVisibility(View.VISIBLE);
            image2_ShoppingActivity.setVisibility(View.VISIBLE);
            tv1_ShoppingActivity.setVisibility(View.VISIBLE);
            tv2_ShoppingActivity.setVisibility(View.VISIBLE);

        }

        if(recipeForShoppingList != null){

            ShoppingAdapter adapter = new ShoppingAdapter(this,recipeForShoppingList);
            rcvShopping.setAdapter(adapter);

            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            rcvShopping.addItemDecoration(itemDecoration);
        }
    }

}