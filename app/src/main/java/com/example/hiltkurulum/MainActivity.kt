package com.example.hiltkurulum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hiltkurulum.model.Category
import com.example.hiltkurulum.model.Meal
import com.example.hiltkurulum.ui.theme.HiltKurulumTheme
import com.example.hiltkurulum.view.CategoryMealFilterEatPage
import com.example.hiltkurulum.view.DetailPage
import com.example.hiltkurulum.view.HomePage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HiltKurulumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                   //HomePage()
                    PageTransitions()
                }
            }
        }
    }
}

@Composable
fun PageTransitions(){

    val navController= rememberNavController()
    NavHost(navController = navController, startDestination ="homePage") {
        composable("homePage"){
            HomePage(navController = navController)
        }
        composable("filterPage/{categoryObject}",
            arguments = listOf(
                navArgument("categoryObject"){type=NavType.StringType}
            )
        ){
            val json=it.arguments?.getString("categoryObject")
           val jsonObject=Gson().fromJson(json,Category::class.java)
            CategoryMealFilterEatPage(categoryObject = jsonObject, navController =navController )
        }
        composable("datailPage/{meal}",
            arguments = listOf(
                navArgument("meal"){type= NavType.StringType}
            )
        ) {
            val jsonMeal = it.arguments?.getString("meal")
            val decodedJsonMeal = URLDecoder.decode(jsonMeal, "UTF-8")
            val mealObject = Gson().fromJson(decodedJsonMeal, Meal::class.java)
            DetailPage(mealObject)
        }


    }
}

