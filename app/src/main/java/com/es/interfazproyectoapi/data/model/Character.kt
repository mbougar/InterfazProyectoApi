package com.es.interfazproyectoapi.data.model

data class Character(
    val fullName: String,
    val nickname: String?,
    val hogwartsHouse: String?,
    val interpretedBy: String?,
    val children: List<String>?,
    val image: String?,
    val birthdate: String?,
    val index: Int
)