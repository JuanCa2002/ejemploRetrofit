package com.example.exampleretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.models.Pokemon
import com.example.exampleretrofit.models.PokemonResponse
import com.example.exampleretrofit.models.RetrofitClient
import com.example.exampleretrofit.pokeApi.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){
    lateinit var adapter: PokemonAdapter
    var listPokemones: ArrayList<Pokemon>? = ArrayList()
    var numero:Int =0
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = RetrofitClient().retrofit
        val service = retrofit.create(PokeApiService::class.java)

        getData(service)
//        getPokemonById(service, 1)

        adapter = PokemonAdapter(listPokemones,this)
        val layoutManager = GridLayoutManager(this, 3)
        binding.pokemons.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.pokemons.layoutManager = layoutManager
        binding.pokemons.adapter = adapter

    }

    fun getData(service:PokeApiService) {
        val pokemonResponseCall = service.obtenerPokemons()
        pokemonResponseCall.enqueue(object : Callback<PokemonResponse> {

            override fun onResponse(call: Call<PokemonResponse>?, response: Response<PokemonResponse>?) {
                if(response!!.isSuccessful){
                    val pokemonRespuesta: PokemonResponse? = response.body()
                    listPokemones = pokemonRespuesta!!.results
                    for (pokemon in listPokemones!!){
                        numero += 1
                        val pokemon: Pokemon = pokemon
                        pokemon.number = numero
                        adapter.adicionarListaPokemon(pokemon)
                        Log.e("Nombre",pokemon.name!!)
                    }
                }else{
                    Log.e("Pokemon",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<PokemonResponse>?, t: Throwable?) {
                Log.e("falla",t!!.message.toString())
            }

        })
    }

//
}