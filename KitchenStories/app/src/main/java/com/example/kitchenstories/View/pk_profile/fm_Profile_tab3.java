package com.example.kitchenstories.View.pk_profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitchenstories.Model.recipe.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.CookingRecipe;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter_OptionFireStore;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fm_Profile_tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fm_Profile_tab3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fm_Profile_tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fm_Profile_tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static fm_Profile_tab3 newInstance(String param1, String param2) {
        fm_Profile_tab3 fragment = new fm_Profile_tab3();
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

    RecyclerView recyclerView_tab3_ProfileActivity;
    RecyclerViewAdapter_OptionFireStore adapterOptionFireStore;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    ImageView image1_Tab3_ProfileActivity;
    View image2_Tab3_ProfileActivity;
    TextView tv1_Tab3_ProfileActivity;
    TextView tv2_Tab3_ProfileActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm__profile_tab3, container, false);

        image1_Tab3_ProfileActivity = view.findViewById(R.id.image1_Tab3_ProfileActivity);
        image2_Tab3_ProfileActivity = view.findViewById(R.id.image2_Tab3_ProfileActivity);
        tv1_Tab3_ProfileActivity = view.findViewById(R.id.tv1_Tab3_ProfileActivity);
        tv2_Tab3_ProfileActivity = view.findViewById(R.id.tv2_Tab3_ProfileActivity);

        image1_Tab3_ProfileActivity.setVisibility(View.GONE);
        image2_Tab3_ProfileActivity.setVisibility(View.GONE);
        tv1_Tab3_ProfileActivity.setVisibility(View.GONE);
        tv2_Tab3_ProfileActivity.setVisibility(View.GONE);


        // get data
        firebaseUser = firebaseAuth.getCurrentUser();
        String currentUser = firebaseUser.getEmail();

        recyclerView_tab3_ProfileActivity = view.findViewById(R.id.recyclerView_tab3_ProfileActivity);
        recyclerView_tab3_ProfileActivity.setLayoutManager(new GridLayoutManager(getContext(), 2));


        Query query = firebaseFirestore.collection("User").document(currentUser)
                .collection("RecipeLiked");


        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {

                    image1_Tab3_ProfileActivity.setVisibility(View.VISIBLE);
                    image2_Tab3_ProfileActivity.setVisibility(View.VISIBLE);
                    tv1_Tab3_ProfileActivity.setVisibility(View.VISIBLE);
                    tv2_Tab3_ProfileActivity.setVisibility(View.VISIBLE);
                }
            }
        });

        FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionFireStore = new RecyclerViewAdapter_OptionFireStore(getContext(), options, new RecyclerViewAdapter_OptionFireStore.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getContext(), CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_tab3_ProfileActivity.setAdapter(adapterOptionFireStore);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterOptionFireStore.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterOptionFireStore.stopListening();
    }

    public void findByIdForComponents() {

    }


}