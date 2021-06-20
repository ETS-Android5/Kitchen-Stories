package com.example.kitchenstories.ViewModel.SearchActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_FilterInSearch extends RecyclerView.Adapter<RecyclerViewAdapter_FilterInSearch.MyViewHolder> implements Filterable {


    Context mContext;
    ArrayList<Recipe> mData;
    ArrayList<Recipe> mData_backup;

    public RecyclerViewAdapter_FilterInSearch(Context mContext, ArrayList<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mData_backup = new ArrayList<>(mData);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_filter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.image_recipe_FilterInSearch.setImageResource(mData.get(position).getImage_CookingRecipe());
        holder.name_recipe_FilterInSearch.setText(mData.get(position).getName_cooking_recipe());
        holder.name_recipeGroup_FilterInSearch.setText(mData.get(position).getName_authorGroup());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_recipe_FilterInSearch;
        private TextView name_recipe_FilterInSearch;
        private TextView name_recipeGroup_FilterInSearch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_recipe_FilterInSearch = itemView.findViewById(R.id.image_recipe_FilterInSearch);
            name_recipe_FilterInSearch = itemView.findViewById(R.id.name_recipe_FilterInSearch);
            name_recipeGroup_FilterInSearch = itemView.findViewById(R.id.name_recipeGroup_FilterInSearch);
        }
    }


    @Override
    public Filter getFilter() {
        return dataFilter;
    }

    private Filter dataFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Recipe> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){

                filteredList.addAll(mData_backup);
            }
            else{

                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Recipe item : mData_backup){

                    if(item.getName_cooking_recipe().toLowerCase().contains(filterPattern)){

                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mData.clear();
            mData.addAll( (ArrayList<Recipe>) results.values);
            notifyDataSetChanged();
        }
    };


}
