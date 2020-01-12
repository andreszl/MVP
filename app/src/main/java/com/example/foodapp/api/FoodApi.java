package com.example.foodapp.api;

import com.example.foodapp.models.Categories;
import com.example.foodapp.models.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("search.php?f=b")
    Call<Meals> getMeals();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealsByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals.Meal> getMealByName(@Query("s") String mealName);
}