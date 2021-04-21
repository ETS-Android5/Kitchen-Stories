package com.example.kitchenstories.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kitchenstories.Model.Contact;
import com.example.kitchenstories.R;
import com.example.kitchenstories.ViewModel.RecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;


public class FragmentContact extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<Contact> lstContact;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.contact_recycleview);
        RecyclerViewAdapter recyclerViewAdater = new RecyclerViewAdapter(getContext(), lstContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdater);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();

        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));
        lstContact.add(new Contact("Lu IU Vi", "Nhieu lam", R.drawable.ic_launcher_background));


    }
}