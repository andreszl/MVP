package com.example.foodapp.activities.category;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.Utils;
import com.example.foodapp.adapters.RecyclerViewMealsByCategory;
import com.example.foodapp.models.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageCategory;
    ImageView imageCategoryBg;
    TextView textCategory;
    CardView cardCategory;

    AlertDialog.Builder descDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageCategory = view.findViewById(R.id.imageCategory);
        imageCategoryBg = view.findViewById(R.id.imageCategoryBg);
        textCategory = view.findViewById(R.id.textCategory);
        cardCategory = view.findViewById(R.id.cardCategory);

        cardCategory.setOnClickListener(view1 -> {
            descDialog.setPositiveButton("CLOSE", ((dialog, i) -> dialog.dismiss()));
            descDialog.show();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            Log.w("onViewCreated", "onViewCreated test arguments");
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
            .into(imageCategory);
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
            .into(imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity())
                .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewMealsByCategory adapter = new RecyclerViewMealsByCategory(getActivity(), meals);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(getActivity(), "meal: " + meals.get(position).getStrMeal(), Toast.LENGTH_SHORT);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

}
