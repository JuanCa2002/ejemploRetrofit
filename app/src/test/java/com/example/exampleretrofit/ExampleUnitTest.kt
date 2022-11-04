package com.example.exampleretrofit

import android.app.Activity
import android.util.Log
import com.example.exampleretrofit.models.Pokemon
import com.example.exampleretrofit.models.PokemonResponse
import com.example.exampleretrofit.models.RetrofitClient
import com.example.exampleretrofit.pokeApi.PokeApiService
import org.junit.Test
import retrofit2.Retrofit


import strikt.api.*
import strikt.assertions.*

class ExampleUnitTest {
    var listPokemones: ArrayList<Pokemon>? = ArrayList()
    val instance: Retrofit = RetrofitClient().retrofit
    val service = instance.create(PokeApiService::class.java)

    @Test
    fun comprobarDatosLista(){
        val call = service.obtenerPokemons()
        val pokemonRespuesta: PokemonResponse? = call.execute().body()
        listPokemones = pokemonRespuesta!!.results
        expectThat(listPokemones!!.size){
            assertThat("El tamaño de la lista "){
                it == 20
            }
        }
        expectThat(listPokemones!![0].name){
            assertThat("El nombre del primer pokemon"){
                it.equals("bulbasaur")
            }
        }


    }
    @Test
    fun comprobarEstadoRespuesta(){
      val call = service.obtenerPokemons()
        expectThat(call.execute()){
            assertThat("La respuesta es ok"){
                it.code() == 200
            }
        }
    }
    @Test
    fun comprobarApi() {
        //Assert that, Retrofit's base url matches to our BASE_URL
        val subject = instance.baseUrl().url().toString()
        expectThat(subject).isEqualTo("https://pokeapi.co/api/v2/")
            .isA<String>()
    }
}