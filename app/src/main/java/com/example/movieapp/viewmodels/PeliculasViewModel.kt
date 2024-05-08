package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Constantes
import com.example.movieapp.models.PeliculaModel
import com.example.movieapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PeliculasViewModel: ViewModel() {
    private var _listaPeliculas = MutableLiveData<List<PeliculaModel>>()

    val listaPeliculas: LiveData<List<PeliculaModel>> = _listaPeliculas

    fun obtenerCartelera(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.obtenerCartelera(Constantes.API_KEY)
            Log.d("movie", "aca")
            withContext(Dispatchers.Main){
                _listaPeliculas.value = response.body()!!.resultados.sortedByDescending { it.votoPromedio }
               // val pelis = response.body()!!.resultados.sortedByDescending { it.votoPromedio }
            }
        }
    }

    fun obtenerPopulares(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.obtenerPopulares(Constantes.API_KEY)
            withContext(Dispatchers.Main){
                _listaPeliculas.value = response.body()!!.resultados.sortedByDescending { it.votoPromedio }
            }
        }
    }


}