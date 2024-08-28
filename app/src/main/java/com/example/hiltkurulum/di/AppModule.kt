package com.example.hiltkurulum.di

import com.example.hiltkurulum.repo.MealRepo
import com.example.hiltkurulum.servis.MealApi
import com.example.hiltkurulum.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("CategoryMealRepo")
    fun getCategoryMealList(@Named("CategoryApi") api: MealApi)=MealRepo(api)


    @Singleton
    @Provides
    @Named("CategoryApi")
    fun getCategory():MealApi{
       return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL)
            .build().create(MealApi::class.java)
    }



    @Singleton
    @Provides
    @Named("FilterEatCategoryMealRepo")
    fun getFilterEatCategoryList(@Named("FilterEatCategoryApi") api: MealApi)=MealRepo(api)

    @Singleton
    @Provides
    @Named("FilterEatCategoryApi")
    fun getFilterEatCategory():MealApi{
        return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL_FILTER)
            .build().create(MealApi::class.java)
    }


}