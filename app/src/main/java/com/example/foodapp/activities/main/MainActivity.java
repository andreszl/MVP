package com.example.foodapp.activities.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.R;
import com.example.foodapp.Utils;
import com.example.foodapp.activities.category.CategoryActivity;
import com.example.foodapp.adapters.RecyclerViewMainAdapter;
import com.example.foodapp.adapters.ViewPagerHeaderAdapter;
import com.example.foodapp.models.Categories;
import com.example.foodapp.models.Meals;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";


    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerMeal = findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = findViewById(R.id.recyclerCategory);

        presenter = new MainPresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meals, this);

        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();


    }

    @Override
    public void setCategories(List<Categories.Category> categories) {
       RecyclerViewMainAdapter mainAdapter = new RecyclerViewMainAdapter(categories, this);
       recyclerViewCategory.setAdapter(mainAdapter);
        @SuppressLint("WrongConstant") GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        mainAdapter.notifyDataSetChanged();

        mainAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) categories);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);

    }
}
