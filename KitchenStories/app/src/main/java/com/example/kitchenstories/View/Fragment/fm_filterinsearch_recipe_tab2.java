package com.example.kitchenstories.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fm_filterinsearch_recipe_tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fm_filterinsearch_recipe_tab2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fm_filterinsearch_recipe_tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fm_filterinsearch_recipe_tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static fm_filterinsearch_recipe_tab2 newInstance(String param1, String param2) {
        fm_filterinsearch_recipe_tab2 fragment = new fm_filterinsearch_recipe_tab2();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_filterinsearch_recipe_tab2, container, false);


        recyclerView = view.findViewById(R.id.recycleview_FilterInSearach_tab2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        keysearch = getArguments().getString("KEYSEARCH_FOR_FRAGMENT_ALLRECIPE");
        Log.d("TEST", keysearch);

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
}