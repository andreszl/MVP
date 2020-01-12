package com.example.foodapp.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.models.Meals;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {

    }

    @Override
    public void onErrorLoading(String message) {

    }
}
