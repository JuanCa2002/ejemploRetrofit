package com.example.exampleretrofit.pokeApi

import retrofit2.http.GET
import com.example.exampleretrofit.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon")
    fun obtenerPokemons(): Call<PokemonResponse>
}