package com.es.interfazproyectoapi.data.model

data class House(
    val house: String,
    val emoji: String?,
    val founder: String?,
    val colors: List<String>?,
    val animal: String?,
    val index: Int
)