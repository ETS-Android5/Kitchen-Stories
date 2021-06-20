package com.example.kitchenstories.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fm_filterinsearch_recipe_tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fm_filterinsearch_recipe_tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fm_filterinsearch_recipe_tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fm_filterinsearch_recipe_tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static fm_filterinsearch_recipe_tab1 newInstance(String param1, String param2) {
        fm_filterinsearch_recipe_tab1 fragment = new fm_filterinsearch_recipe_tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private List<Recipe> mData;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    RecyclerViewAdapter_OptionFireStore adapter_optionFireStore;

    private String keysearch;

    private View image_error_fm_filterinsearchAllRecipe_tab1;
    private TextView tv1_error_fm_filterinsearchAllRecipe_tab1;
    private TextView tv2_error_fm_filterinsearchAllRecipe_tab1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fm_filterinsearch_recipe_tab1, container, false);


        //findByIdForComponents(view);
        image_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.image_error_fm_filterinsearchAllRecipe_tab1);
        tv1_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.tv1_error_fm_filterinsearchAllRecipe_tab1);
        tv2_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.tv2_error_fm_filterinsearchAllRecipe_tab1);

        image_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);
        tv1_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);
        tv2_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);


        recyclerView = view.findViewById(R.id.recycleview_FilterInSearach_tab1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        keysearch = getArguments().getString("KEYSEARCH_FOR_FRAGMENT_ALLRECIPE");


        if(keysearch.isEmpty()){
            Query query = firebaseFirestore.collection("Recipe");

            FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
                    .setQuery(query, Recipe.class)
                    .build();

            adapter_optionFireStore = new RecyclerViewAdapter_OptionFireStore(getContext(), options, new RecyclerViewAdapter_OptionFireStore.OnItemClickListener() {
                @Override
                public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                    Intent intent = new Intent(getContext(), CookingRecipe.class);
                    intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter_optionFireStore);
        }
        else {

            Query query = firebaseFirestore.collection("Recipe")
                    .whereEqualTo("tags." + keysearch, true);

            // check query is empty or not
            query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if(queryDocumentSnapshots.isEmpty()){

                        image_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.VISIBLE);
                        tv1_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.VISIBLE);
                        tv2_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.VISIBLE);
                    }
                }
            });


            FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
                    .setQuery(query, Recipe.class)
                    .build();


            adapter_optionFireStore = new RecyclerViewAdapter_OptionFireStore(getContext(), options, new RecyclerViewAdapter_OptionFireStore.OnItemClickListener() {
                @Override
                public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                    Intent intent = new Intent(getContext(), CookingRecipe.class);
                    intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter_optionFireStore);
        }





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter_optionFireStore.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter_optionFireStore.stopListening();
    }

//    private void findByIdForComponents(View view){
//
//        image_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.image_error_fm_filterinsearchAllRecipe_tab1);
//        tv1_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.tv1_error_fm_filterinsearchAllRecipe_tab1);
//        tv2_error_fm_filterinsearchAllRecipe_tab1 = view.findViewById(R.id.tv2_error_fm_filterinsearchAllRecipe_tab1);
//
//        image_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);
//        tv1_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);
//        tv1_error_fm_filterinsearchAllRecipe_tab1.setVisibility(View.GONE);
//    }




}