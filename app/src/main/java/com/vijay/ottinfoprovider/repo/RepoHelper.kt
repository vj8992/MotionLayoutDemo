package com.vijay.ottinfoprovider.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepoHelper {

  private const val baseUrl = "http://143.110.248.51:8070"
  const val imageUrl = "http://143.110.248.51/"

  fun getInstance(): Retrofit{
    return Retrofit.Builder().baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}