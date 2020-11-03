package com.example.retrofit


import com.google.gson.annotations.SerializedName

data class NewsApiJSON(
    @SerializedName("news")
    val news: List<New>,
    @SerializedName("status")
    val status: String
)