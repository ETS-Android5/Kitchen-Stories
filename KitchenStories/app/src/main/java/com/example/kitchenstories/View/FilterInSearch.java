package com.example.kitchenstories.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.filterInSearch_AllRecipe.FilterInSearchAllRecipe;
import com.example.kitchenstories.ViewModel.SearchActivity.RecyclerViewAdapter_Option_FilterInSearch;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class FilterInSearch extends AppCompatActivity {

    private ArrayList<Recipe> mData;

    private SearchView searchView;
    private RecyclerView recyclerView_FilterInSearch;

    private RecyclerViewAdapter_Option_FilterInSearch adapter_option_filterInSearch;

    private Button button_back_FilterInSearch;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

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
        findByIdForComponents();

        button_back_FilterInSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView_FilterInSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        initData();
//        adapter_filterInSearch = new RecyclerViewAdapter_FilterInSearch(this, mData);
//        recyclerView_FilterInSearch.setAdapter(adapter_filterInSearch);


        Query query = firebaseFirestore.collection("Recipe");

        FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapter_option_filterInSearch = new RecyclerViewAdapter_Option_FilterInSearch(FilterInSearch.this, options, new RecyclerViewAdapter_Option_FilterInSearch.OnItemClickListener() {
            @Override
            public void onClickListener(DocumentSnapshot documentSnapshot, int position) {

                Intent intent = new Intent(FilterInSearch.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_FilterInSearch.setAdapter(adapter_option_filterInSearch);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(FilterInSearch.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                Query query1;

                if(newText.isEmpty()){
                    query1 = firebaseFirestore.collection("Recipe");
                }
                else{
                    query1 = firebaseFirestore.collection("Recipe")
                            .whereEqualTo("tags." + newText.toString(), true);
                }

                FirestoreRecyclerOptions<Recipe> options1 = new FirestoreRecyclerOptions.Builder<Recipe>()
                        .setQuery(query1, Recipe.class)
                        .build();

                adapter_option_filterInSearch.updateOptions(options1);

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter_option_filterInSearch.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter_option_filterInSearch.stopListening();
    }

    public void findByIdForComponents(){
        recyclerView_FilterInSearch = findViewById(R.id.recyclerView_FilterInSearch);

        button_back_FilterInSearch = findViewById(R.id.button_back_FilterInSearch);

        searchView = findViewById(R.id.searchView_FilterInSearch);
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