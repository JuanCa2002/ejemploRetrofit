package com.example.exampleretrofit.models

class Pokemon {
    var name: String? = null
    var number: Int? = null
    var url: String? = null


    override fun toString(): String {
        return "Pokemon(name=$name, number=$number)"
    }


}