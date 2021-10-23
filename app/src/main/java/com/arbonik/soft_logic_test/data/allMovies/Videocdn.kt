package com.arbonik.soft_logic_test.data.allMovies

data class Videocdn(
    val duration: String? = null,
    val quality: List<String>? = listOf(),
    val url: List<String>? = listOf(),
    val voice: List<String>? = listOf()
)