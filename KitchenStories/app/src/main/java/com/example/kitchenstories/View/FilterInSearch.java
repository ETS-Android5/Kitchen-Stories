package com.example.kitchenstories.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.SearchActivity.RecyclerViewAdapter_FilterInSearch;

import java.util.ArrayList;

public class FilterInSearch extends AppCompatActivity {

    private ArrayList<Recipe> mData;

    private SearchView searchView;

    private RecyclerView recyclerView_FilterInSearch;
    private RecyclerViewAdapter_FilterInSearch adapter_filterInSearch;

    private Button button_back_FilterInSearch;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_in_search);

        //
        transparentStatusAndNavigation();

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


        // FIND BY ID
        recyclerView_FilterInSearch = findViewById(R.id.recyclerView_FilterInSearch);

        button_back_FilterInSearch = findViewById(R.id.button_back_FilterInSearch);

        searchView = findViewById(R.id.searchView_FilterInSearch);



        button_back_FilterInSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        recyclerView_FilterInSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initData();
        adapter_filterInSearch = new RecyclerViewAdapter_FilterInSearch(this, mData);
        recyclerView_FilterInSearch.setAdapter(adapter_filterInSearch);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter_filterInSearch.getFilter().filter(newText);
                return false;
            }
        });
    }


    public void initData(){

        mData = new ArrayList<>();

        mData.add(new Recipe(R.drawable.ic_baseline_add_circle, "one", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_favorite_24, "two", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_home, "three", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_outline_watch_later_24, "four", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_person, "five", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_outline_date_range_24, "sixe", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_add_circle, "one", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_favorite_24, "two", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_home, "three", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_outline_watch_later_24, "four", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_person, "five", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_outline_date_range_24, "sixe", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_add_circle, "one", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_favorite_24, "two", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_home, "three", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_outline_watch_later_24, "four", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_baseline_person, "five", "Kitchen Stories"));

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