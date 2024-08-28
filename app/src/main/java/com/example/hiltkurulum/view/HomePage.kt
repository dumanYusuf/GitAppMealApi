package com.example.hiltkurulum.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.hiltkurulum.model.Category
import com.example.hiltkurulum.model.Meal
import com.example.hiltkurulum.viewModel.HomePageViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
    viewModel: HomePageViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Meal Category List") })
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(viewModel.categoryMealList.value) { category ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = category.strCategory,
                            color = Color.Red,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Log.e("CategoryImage", "Category URL: ${category.strCategoryThumb}")
                        Image(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(200.dp, 200.dp)
                                .clip(CircleShape)
                                .clickable {
                                    val jsonMeal = Gson().toJson(category)
                                    val encodedJsonMeal = URLEncoder.encode(jsonMeal, "UTF-8")
                                    navController.navigate("filterPage/$encodedJsonMeal")
                                },
                            painter = rememberImagePainter(data = category.strCategoryThumb),
                            contentDescription = category.strCategoryThumb)
                    }
                }
            }
        }
    )
}
