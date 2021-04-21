package com.example.kitchenstories.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kitchenstories.Model.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter;

import java.util.ArrayList;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fm_recipe_tab_1, container, false);

        View view = inflater.inflate(R.layout.fragment_fm_recipe_tab_1, container, false);

        recyclerView = view.findViewById(R.id.recipe_recycleview_tab1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mData);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = new ArrayList<>();

        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));
        mData.add(new Recipe(R.drawable.ic_launcher_background, "chicken", R.drawable.ic_launcher_background, "Thang", "Kitchen Stories"));


    }


}