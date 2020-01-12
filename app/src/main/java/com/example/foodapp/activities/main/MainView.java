package com.example.foodapp.activities.main;

import com.example.foodapp.models.Categories;
import com.example.foodapp.models.Meals;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meal);
    void setCategories(List<Categories.Category> category);
    void onErrorLoading(String message);
}
