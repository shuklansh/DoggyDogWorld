package com.shuklansh.doggydogworld.DogApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogHelper {

    val BASE_URL = "https://dog.ceo/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}