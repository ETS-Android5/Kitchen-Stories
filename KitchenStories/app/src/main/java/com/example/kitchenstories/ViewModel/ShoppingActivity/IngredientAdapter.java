package com.example.kitchenstories.ViewModel.ShoppingActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitchenstories.Model.DBHelper;
import com.example.kitchenstories.Model.shopping.IngredientShopping;
import com.example.kitchenstories.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    Context mContext;
    private List<IngredientShopping> ingreList;
    DBHelper dbHelper;

    public IngredientAdapter(Context mcontext,List<IngredientShopping> ingredientShoppingList) {
        this.mContext = mcontext;
        this.ingreList = ingredientShoppingList;
        dbHelper = new DBHelper(mcontext);
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_shopping_ingre,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        IngredientShopping ingre = ingreList.get(position);
        if(ingre==null){
            return;
        }
        holder.tvNameIngre.setText(ingre.getNameIngre());
        holder.tvAmountIngre.setText(ingre.getAmountIngre());


        if(ingreList.get(position).getStatus() == 0){
            holder.cb_status.setChecked(false);
        }
        if (ingreList.get(position).getStatus() == 1){
            holder.cb_status.setChecked(true);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ingreList.get(position).getStatus() == 0){
                    IngredientShopping ingredientShopping = new IngredientShopping(ingre.getId(),
                            ingre.getIdShopping(),
                            ingre.getNameIngre(),
                            ingre.getAmountIngre(),
                            1);
                    dbHelper.updateStatusIngre(ingre.getId(),1);
                    ingreList.set(position,ingredientShopping);
                    notifyDataSetChanged();
                }
                else{
                    IngredientShopping ingredientShopping = new IngredientShopping(ingre.getId(),
                            ingre.getIdShopping(),
                            ingre.getNameIngre(),
                            ingre.getAmountIngre(),
                            0);
                    ingreList.set(position,ingredientShopping);
                    dbHelper.updateStatusIngre(ingre.getId(), 0);
                    notifyDataSetChanged();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return ingreList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameIngre;
        private TextView tvAmountIngre;
        private CheckBox cb_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cb_status = itemView.findViewById(R.id.checkBox_ingredient);
            tvNameIngre = itemView.findViewById(R.id.name_ingredient);
            tvAmountIngre = itemView.findViewById(R.id.amount_ingredient);
        }
    }
}
