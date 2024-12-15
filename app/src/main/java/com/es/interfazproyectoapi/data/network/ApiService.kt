package com.es.interfazproyectoapi.data.network

import com.es.interfazproyectoapi.data.model.Book
import com.es.interfazproyectoapi.data.model.Character
import com.es.interfazproyectoapi.data.model.House
import com.es.interfazproyectoapi.data.model.Spell
import retrofit2.http.GET

interface ApiService {

    @GET("es/characters")
    suspend fun getCharacters(): List<Character>

    @GET("es/spells")
    suspend fun getSpells(): List<Spell>

    @GET("es/books")
    suspend fun getBooks(): List<Book>

    @GET("es/houses")
    suspend fun getHouses(): List<House>
}