package com.soffanma.vtune

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface relayInterface {

    @GET("/")
    fun sendCode(@Query("code") code: String?): Call<Void>

}