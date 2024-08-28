package com.example.hiltkurulum.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.hiltkurulum.model.Category
import com.example.hiltkurulum.model.Meal
import com.example.hiltkurulum.viewModel.CategoryMealFilterEatPageViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryMealFilterEatPage(
    categoryObject: Category,
    navController: NavController,
    filterViewModel: CategoryMealFilterEatPageViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        filterViewModel.loadCategoryMealFilterList(categoryObject)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Category: ${categoryObject.strCategory}") })
        },
        content = { paddingValues ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(filterViewModel.filterMealEat.value) { filterMeal ->
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = filterMeal.strMeal,
                                color = Color.Red,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 4.dp),
                            )
                            Image(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(200.dp)
                                    .clickable {
                                        val mealObject = Meal(
                                            filterMeal.idMeal,
                                            filterMeal.strMeal,
                                            filterMeal.strMealThumb
                                        )
                                        val jsonMeal = Gson().toJson(mealObject)
                                        val encodedJsonMeal = URLEncoder.encode(jsonMeal, "UTF-8")
                                        navController.navigate("datailPage/$encodedJsonMeal")
                                    },
                                painter = rememberImagePainter(data = filterMeal.strMealThumb),
                                contentDescription = filterMeal.strMealThumb
                            )
                        }
                    }
                }

        }
    )
}
