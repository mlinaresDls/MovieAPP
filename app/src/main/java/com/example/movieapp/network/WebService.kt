package com.example.movieapp.network

import com.example.movieapp.network.response.PeliculasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun obtenerCartelera(
        @Query("api_key") apiKey: String
    ): Response <PeliculasResponse>

    @GET("popular")
    suspend fun obtenerPopulares(
        @Query("api_key") apiKey: String
    ): Response <PeliculasResponse>
}