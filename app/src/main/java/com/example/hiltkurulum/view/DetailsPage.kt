package com.example.hiltkurulum.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hiltkurulum.model.Meal

import coil.compose.rememberImagePainter as rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPage(meal:Meal){
    Scaffold(
        topBar =
        { 
            TopAppBar(title = { Text(text = meal.strMeal)})
        },
        content = {
            Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    modifier = Modifier
                        .padding().fillMaxSize()
                        .size(200.dp),
                    painter = rememberImagePainter(data = meal.strMealThumb),
                    contentDescription = "")
                
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = meal.strMeal)
            }
        }
    )
}