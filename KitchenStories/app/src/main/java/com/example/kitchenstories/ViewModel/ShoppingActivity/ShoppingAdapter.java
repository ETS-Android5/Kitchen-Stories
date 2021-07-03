package com.example.kitchenstories.ViewModel.ShoppingActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.kitchenstories.Model.DBHelper;
import com.example.kitchenstories.Model.shopping.RecipeForShopping;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.ShoppingRecipe;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    Context context;
    private List<RecipeForShopping> shoppingList;
    DBHelper dbHelper;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public ShoppingAdapter(Context mcontext,List<RecipeForShopping> shoppingList) {
        this.context = mcontext;
        this.shoppingList = shoppingList;
        dbHelper = new DBHelper(mcontext);
    }


    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shopping,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingAdapter.ViewHolder holder, int position) {
        RecipeForShopping recipe = shoppingList.get(position);
        if(recipe==null){
            return;
        }

        viewBinderHelper.bind(holder.swipeRevealLayout,String.valueOf(recipe.getId()));
        holder.image_shopping.setImageBitmap(recipe.getImage());
        holder.name_shopping.setText(recipe.getName());
        holder.ingredient_shopping.setText(String.valueOf(dbHelper.getCountIngreStatus0(recipe.getId())+" ingredients missing"));


        holder.layoutRecipeShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingRecipe.class);
                intent.putExtra("ID_SHOPPING_RECIPE", recipe.getId());
                context.startActivity(intent);
            }
        });

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingList.remove(position);
                dbHelper.deleteShopping(recipe.getId());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        private LinearLayout layoutRecipeShopping;
        private ImageView image_shopping;
        private TextView name_shopping;
        private TextView ingredient_shopping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
            layoutDelete = itemView.findViewById(R.id.layout_delete);
            layoutRecipeShopping = itemView.findViewById(R.id.layout_RecipeShopping);
            image_shopping = itemView.findViewById(R.id.image_shopping);
            name_shopping = itemView.findViewById(R.id.name_shopping);
            ingredient_shopping = itemView.findViewById(R.id.ingredient_shoppingItem);
        }
    }
}
