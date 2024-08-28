package com.example.hiltkurulum.servis

import com.example.hiltkurulum.model.FilterCategoryEat
import com.example.hiltkurulum.model.MealCategoryList
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {


    @GET("/api/json/v1/1/categories.php")
    suspend fun mealCategoryList():MealCategoryList


    @GET("/api/json/v1/1/filter.php?")
    suspend fun filterEatCategory(
        @Query(
            value = "c"
        ) c: String
    ): FilterCategoryEat

}