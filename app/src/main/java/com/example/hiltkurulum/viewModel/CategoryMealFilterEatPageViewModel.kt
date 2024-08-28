package com.example.hiltkurulum.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import com.example.hiltkurulum.model.Category
import com.example.hiltkurulum.model.Meal
import com.example.hiltkurulum.repo.MealRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CategoryMealFilterEatPageViewModel @Inject constructor(
    @Named("FilterEatCategoryMealRepo")
    private val repo:MealRepo):ViewModel() {

    var filterMealEat= mutableStateOf<List<Meal>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)


    fun loadCategoryMealFilterList(category: Category){
        viewModelScope.launch {
            isLoading.value = true

            val result = repo.getCategoryFilterEat(category.strCategory)
            when (result) {
                is Resource.Success -> {
                    val data = result.data
                    if (data != null && data.meals != null) {
                        val categoryFilterItem = data.meals.mapIndexed { index, item ->
                            Meal(item.idMeal, item.strMeal, item.strMealThumb)

                        }
                        isLoading.value = false
                        errorMessage.value = ""
                        filterMealEat.value += categoryFilterItem
                    }
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else -> {
                    Log.e("viewModel", "Unknown error")
                    isLoading.value = false
                }
            }
        }
    }

}