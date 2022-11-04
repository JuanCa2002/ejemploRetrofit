package com.example.exampleretrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exampleretrofit.models.Pokemon


class PokemonAdapter(var pokemones:ArrayList<Pokemon>?, var context: Context): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon,parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        holder.bind(pokemones!![position])
    }

    override fun getItemCount() = pokemones!!.size

    fun adicionarListaPokemon(pokemon: Pokemon) {
        pokemones!!.add(pokemon)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val name: TextView = itemView.findViewById(R.id.name_pokemon)
        var sizePokemon = 0
        val imageView: ImageView = itemView.findViewById(R.id.image_pokemon)

        init {
            itemView.setOnClickListener (this)
        }

        fun bind(pokemon: Pokemon){
            Log.e("numero", pokemon.number.toString())
            name.text = pokemon.name
            Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.number}.png")
                .into(imageView)
        }

        override fun onClick(p0: View?) {

        }
    }

}