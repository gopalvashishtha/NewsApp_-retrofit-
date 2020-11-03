package com.example.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY="c7dccd29587f4635840658a96c6236fc"
interface service {
    @GET("/v1/latest-news?language=it&apiKey=WFhTOWlOpQYjlRRg-8Owb17TJgoiFX5AVHiDA_wzLNywbGjj")
    suspend fun getNews() : NewsApiJSON
}


//
//object newsService{
//    val newsInstance:service
//
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            newsInstance=retrofit.create(service::class.java)
//    }
//}