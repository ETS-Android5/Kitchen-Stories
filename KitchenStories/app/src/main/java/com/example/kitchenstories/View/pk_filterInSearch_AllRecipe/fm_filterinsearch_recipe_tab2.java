package com.example.kitchenstories.View.pk_filterInSearch_AllRecipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kitchenstories.Model.recipe.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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


    private View image_error_fm_filterinsearchAllRecipe_tab2;
    private TextView tv1_error_fm_filterinsearchAllRecipe_tab2;
    private TextView tv2_error_fm_filterinsearchAllRecipe_tab2;

    FirestoreRecyclerOptions<Recipe> options;
    Query query;

    private String keysearch;
    String sortExtra = "";
    String categoryExtra = "";
    String dietExtra = "";
    String cuisineExtra = "";
    ArrayList<String> occasionExtra = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_filterinsearch_recipe_tab2, container, false);

        //findByIdForComponents(view);
        image_error_fm_filterinsearchAllRecipe_tab2 = view.findViewById(R.id.image_error_fm_filterinsearchAllRecipe_tab2);
        tv1_error_fm_filterinsearchAllRecipe_tab2 = view.findViewById(R.id.tv1_error_fm_filterinsearchAllRecipe_tab2);
        tv2_error_fm_filterinsearchAllRecipe_tab2 = view.findViewById(R.id.tv2_error_fm_filterinsearchAllRecipe_tab2);

        image_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.GONE);
        tv1_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.GONE);
        tv2_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.GONE);

        recyclerView = view.findViewById(R.id.recycleview_FilterInSearach_tab2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


//        keysearch = getArguments().getString("KEYSEARCH_FOR_FRAGMENT_ALLRECIPE");

        if (!getArguments().getString("KEYSEARCH_FOR_FRAGMENT_ALLRECIPE").isEmpty()) {
            keysearch = getArguments().getString("KEYSEARCH_FOR_FRAGMENT_ALLRECIPE");
        }
        if (!getArguments().getString("sortExtra").isEmpty()) {
            sortExtra = getArguments().getString("sortExtra");
        }
        if (!getArguments().getString("categoryExtra").isEmpty()) {
            categoryExtra = getArguments().getString("categoryExtra");
        }
        if (!getArguments().getString("dietExtra").isEmpty()) {
            dietExtra = getArguments().getString("dietExtra");
        }
        if (!getArguments().getString("cuisineExtra").isEmpty()) {
            cuisineExtra = getArguments().getString("cuisineExtra");
        }
        if (!getArguments().getStringArrayList("occasionExtra").isEmpty()) {
            occasionExtra = getArguments().getStringArrayList("occasionExtra");

//            for (int i = 0; i < occasionExtra.size(); i++) {
//                Log.d("TESTARRAY", occasionExtra.get(i).toString()+ " null");
//            }
        }

//        if(keysearch.isEmpty()){
//            query = firebaseFirestore.collection("Recipe");
//
//            FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
//                    .setQuery(query, Recipe.class)
//                    .build();
//
//            adapter_optionFireStore = new RecyclerViewAdapter_OptionFireStore(getContext(), options, new RecyclerViewAdapter_OptionFireStore.OnItemClickListener() {
//                @Override
//                public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                    Intent intent = new Intent(getContext(), CookingRecipe.class);
//                    intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
//                    startActivity(intent);
//                }
//            });
//
//            recyclerView.setAdapter(adapter_optionFireStore);
//        }
//        else {
//
//            query = firebaseFirestore.collection("Recipe")
//                    .whereEqualTo("tags." + keysearch, true)
//                    .whereEqualTo("name_authorGroup", "Community");
//
//            // check query is empty or not
//            query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                @Override
//                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                    if(queryDocumentSnapshots.isEmpty()){
//
//                        image_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
//                        tv1_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
//                        tv2_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
//                    }
//                }
//            });
//
//
//        }



        if (!sortExtra.isEmpty()) {

            Log.d("TESTEXTRA", sortExtra);
            if (!categoryExtra.isEmpty()) {

                Log.d("TESTEXTRA", categoryExtra);

                if (!dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");

                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());

                            }

                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");

                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }

                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);
                        }

                    }
                } else if (dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", "null dietExtra: " + dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }



                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }

                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + categoryExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                        }

                    }
                }

            } else if (categoryExtra.isEmpty()) {

                Log.d("TESTEXTRA", "null categoryExtra: " + categoryExtra);

                if (!dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }



                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);
                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + dietExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }

                        }

                    }

                } else if (dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", "null dietExtra: " + dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("tags." + cuisineExtra, true)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }

                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereArrayContainsAny("tagsSort", occasionExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }


                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            if(sortExtra.equals("likeAmount") || sortExtra.equals("ratingAmount")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra, Query.Direction.DESCENDING)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }
                            else if (sortExtra.equals("caloriesSort") || sortExtra.equals("preparationTimeSort")){

                                query = firebaseFirestore.collection("Recipe")
                                        .orderBy(sortExtra)
                                        .whereEqualTo("name_authorGroup", "Community");
                            }

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);
                        }

                    }
                }

            }


        } else if ((sortExtra.isEmpty())) {
            Log.d("TESTEXTRA", "null sortExtra: " + sortExtra);

            if (!categoryExtra.isEmpty()) {

                Log.d("TESTEXTRA", categoryExtra);

                if (!dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");

                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");

                        }

                    }
                } else if (dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", "null dietExtra: " + dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + categoryExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    }
                }

            } else if (categoryExtra.isEmpty()) {

                Log.d("TESTEXTRA", "null categoryExtra: " + categoryExtra);

                if (!dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }

                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + dietExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    }

                } else if (dietExtra.isEmpty()) {

                    Log.d("TESTEXTRA", "null dietExtra: " + dietExtra);

                    if (!cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("tags." + cuisineExtra, true)
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    } else if (cuisineExtra.isEmpty()) {

                        Log.d("TESTEXTRA", "null cuisineExtra: " + cuisineExtra);

                        if (!occasionExtra.isEmpty()) {

                            query = firebaseFirestore.collection("Recipe")
                                    .whereArrayContainsAny("tagsSort", occasionExtra)
                                    .whereEqualTo("name_authorGroup", "Community");

                            for (int i = 0; i < occasionExtra.size(); i++) {
                                Log.d("TESTEXTRA", occasionExtra.get(i).toString());
                            }
                        } else if (occasionExtra.isEmpty()) {

                            Log.d("TESTEXTRA", "null occasionExtra: " + occasionExtra);

                            query = firebaseFirestore.collection("Recipe")
                                    .whereEqualTo("name_authorGroup", "Community");
                        }

                    }
                }

            }
        }




        if (!keysearch.equals("Filter")) {

            query = firebaseFirestore.collection("Recipe")
                    .whereEqualTo("tags." + keysearch, true)
                    .whereEqualTo("name_authorGroup", "Community");

        }

        // check query is empty or not
        query.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (queryDocumentSnapshots.isEmpty()) {

                            image_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
                            tv1_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
                            tv2_error_fm_filterinsearchAllRecipe_tab2.setVisibility(View.VISIBLE);
                        }
                    }
                });

        options = new FirestoreRecyclerOptions.Builder<Recipe>()
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
}