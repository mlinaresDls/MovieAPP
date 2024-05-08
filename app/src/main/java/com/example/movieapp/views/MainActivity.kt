package com.example.movieapp.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodels.PeliculasViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PeliculasViewModel
    private lateinit var adapterPeliculas: AdapterPeliculas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PeliculasViewModel::class.java]

        setupRecyclerView()

        viewModel.listaPeliculas.observe(this){
            adapterPeliculas.listaPeliculas = it
            adapterPeliculas.notifyDataSetChanged()
        }

        binding.cvCartelera.setOnClickListener{
            viewModel.obtenerCartelera()
            cambiarColorBoton("car")
        }

        binding.cvPopulares.setOnClickListener{
            viewModel.obtenerPopulares()
            cambiarColorBoton("pop")
        }

        viewModel.obtenerCartelera()
    }

    private fun cambiarColorBoton(boton: String){
        when(boton){
            "car" -> {
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.verde))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.azul))
            }
            "pop" -> {
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.azul))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.verde))
            }
        }
    }

    private fun setupRecyclerView(){

        var layoutManager = GridLayoutManager(this, 3)
        binding.rvPeliculas.layoutManager = layoutManager
        adapterPeliculas = AdapterPeliculas(this, arrayListOf())
        binding.rvPeliculas.adapter = adapterPeliculas
    }
}