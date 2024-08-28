package com.example.hiltkurulum.repo


import android.util.Log
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import com.example.hiltkurulum.model.FilterCategoryEat
import com.example.hiltkurulum.model.MealCategoryList
import com.example.hiltkurulum.servis.MealApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class   MealRepo  @Inject constructor (private val api:MealApi){


   suspend fun getMealListCategory():Resource<MealCategoryList>{

       val resposnse=try {
           api.mealCategoryList()
       }
       catch (e:Exception){
           return Resource.Error("Error")
       }
         return Resource.Success(resposnse)
   }


    suspend fun getCategoryFilterEat(categoryStr: String):Resource<FilterCategoryEat>{
        val resposnse=try {
            api.filterEatCategory(c = categoryStr )
        }
        catch (e:Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(resposnse)
    }
}