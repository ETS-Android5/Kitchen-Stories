package com.example.kitchenstories.View.CreateRecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.Model.Recipe.StepsForRecipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;
import com.example.kitchenstories.View.Create;
import com.example.kitchenstories.View.MainActivity;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Ingredient_CookingRecipe;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Option_Steps;
import com.example.kitchenstories.ViewModel.CreateActivity.RecycleViewAdapter_Step;
import com.example.kitchenstories.ViewModel.CreateActivity.StepAdapter;
import com.example.kitchenstories.ViewModel.ShoppingActivity.ShoppingAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class CreateStep extends AppCompatActivity {

    private String documentRef;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private RecyclerView rcvStep;
    private RecycleViewAdapter_Step adapter;

    private Button button_add;

    private Button button_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_step);

        if(getIntent().hasExtra("documentRef")){
            documentRef = getIntent().getExtras().getString("documentRef");
        }

        LoadData();

        button_add = findViewById(R.id.btn_Add_CreateStep_Activity);
        button_finish=findViewById(R.id.btn_Finish_CreateStepActivity);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAddNewStep();
            }
        });

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity();
            }
        });
    }

    public void LoadData(){

        rcvStep = findViewById(R.id.rcv_steps_Create);

        Query query = firebaseFirestore.document(documentRef).collection("Steps");

        FirestoreRecyclerOptions<StepsForRecipe> options = new FirestoreRecyclerOptions.Builder<StepsForRecipe>()
                .setQuery(query, StepsForRecipe.class)
                .build();

        rcvStep.setLayoutManager(new LinearLayoutManager(CreateStep.this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecycleViewAdapter_Step(options, CreateStep.this);
        rcvStep.setAdapter(adapter);
    }

    public void gotoAddNewStep(){
        Intent intent = new Intent(CreateStep.this, AddNewStep.class);
        intent.putExtra("documentRef", documentRef);
        startActivity(intent);
    }

    public void gotoMainActivity(){
        Intent intent = new Intent(CreateStep.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}