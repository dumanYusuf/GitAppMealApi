package com.example.hiltkurulum.viewModel

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import com.example.hiltkurulum.model.Category
import com.example.hiltkurulum.model.MealCategoryList
import com.example.hiltkurulum.repo.MealRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class HomePageViewModel @Inject constructor(
    @Named("CategoryMealRepo")
    private val repo: MealRepo):ViewModel(){


    var categoryMealList= mutableStateOf<List<Category>>(listOf())// successs state
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)


    init {
        loadCategoryMealList()
    }

    fun loadCategoryMealList(){
        viewModelScope.launch {
            isLoading.value=true
            val result=repo.getMealListCategory()
            when(result){
                is Resource.Success->{
                    val categoryItem=result.data!!.categories.mapIndexed { index, category ->
                        Category(category.idCategory,category.strCategory,category.strCategoryThumb,category.strCategoryDescription)

                    } as List<Category>

                    Log.e("data","dataaa")

                    errorMessage.value=""
                    isLoading.value=false
                    categoryMealList.value +=categoryItem

                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    Log.e("viewModel", "Error : ${result.message}")

                }

                else->{
                    Log.e("viewModel", "Unknown error")
                }
            }
        }

    }
}