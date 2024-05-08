package com.example.movieapp.network.response

import com.example.movieapp.models.PeliculaModel
import com.google.gson.annotations.SerializedName

data class PeliculasResponse(
    @SerializedName("results")
    var resultados: List<PeliculaModel>
)

