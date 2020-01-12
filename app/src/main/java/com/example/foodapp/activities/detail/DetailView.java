package com.example.foodapp.activities.detail;

import com.example.foodapp.models.Meals;

import java.util.List;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}
