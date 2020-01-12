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
import com.example.foodapp.models.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMainAdapter extends RecyclerView.Adapter<RecyclerViewMainAdapter.RecyclerViewHolder> {
    private List<Categories.Category> categories;
    private Context context;
    public static ClickListener clickListener;

    public RecyclerViewMainAdapter(List<Categories.Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_category,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        String strCategoryThumb = categories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThumb).placeholder(R.drawable.ic_circle).into(holder.categoryThumb);

        String strCategoryName = categories.get(i).getStrCategory();
        holder.categoryName.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView categoryThumb;
        TextView categoryName;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryThumb = itemView.findViewById(R.id.categoryThumb);
            categoryName = itemView.findViewById(R.id.categoryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMainAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
