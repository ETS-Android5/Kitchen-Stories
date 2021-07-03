package com.example.kitchenstories.View.pk_allRecipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kitchenstories.Model.recipe.Recipe;
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
 * Use the {@link fm_recipe_tab_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fm_recipe_tab_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fm_recipe_tab_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fm_recipe_tab_1.
     */
    // TODO: Rename and change types and number of parameters
    public static fm_recipe_tab_1 newInstance(String param1, String param2) {
        fm_recipe_tab_1 fragment = new fm_recipe_tab_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        //return inflater.inflate(R.layout.fragment_fm_recipe_tab_1, container, false);

        View view = inflater.inflate(R.layout.fragment_fm_recipe_tab_1, container, false);

        recyclerView = view.findViewById(R.id.recipe_recycleview_tab1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



        Query query = firebaseFirestore.collection("Recipe")
                .whereNotEqualTo("name_authorGroup", "Community");

//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("comfort food");
//        arrayList.add("weeknight dinner");


//
//        Query query = firebaseFirestore.collection("Recipe")
//                .orderBy("caloriesSort")
//                .whereEqualTo("tags.main", true)
//                .whereEqualTo("tags.Meatless", true)
//                .whereEqualTo("tags.Chinese", true)
//                .whereArrayContainsAny("tagsSort", arrayList);

//        Query query = firebaseFirestore.collection("Recipe")
//                .orderBy("caloriesSort")
//                .whereEqualTo("tags.main", true)
//                .whereEqualTo("tags.Asian", true)
//                .whereArrayContainsAny("tagsSort", Arrays.asList("comfort food", "weeknight diner"));

//        query = firebaseFirestore.collection("Recipe")
//                .orderBy(sortExtra)
//                .whereEqualTo("tags." + categoryExtra, true)
//                .whereEqualTo("tags." + dietExtra, true)
//                .whereEqualTo("tags." + cuisineExtra, true)
//                .whereArrayContainsAny("tagsSort", occasionExtra);



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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




}