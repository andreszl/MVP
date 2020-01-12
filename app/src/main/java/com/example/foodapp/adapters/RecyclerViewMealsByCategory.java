package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMealsByCategory extends RecyclerView.Adapter<RecyclerViewMealsByCategory.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewMealsByCategory(Context context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewMealsByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal, parent, false);
       return  new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealsByCategory.RecyclerViewHolder holder, int position) {
        String strMealThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.ic_circle).into(holder.mealThumb);

        String strMealName = meals.get(position).getStrMeal();
        holder.mealName.setText(strMealName);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mealThumb;
        TextView mealName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb = itemView.findViewById(R.id.mealThumb);
            mealName = itemView.findViewById(R.id.mealName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMealsByCategory.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
