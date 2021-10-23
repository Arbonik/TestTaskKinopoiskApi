package com.arbonik.soft_logic_test.data.allMovies

data class MoviesPage(
    val movies: List<Movy> = listOf(),
    val pagination: Pagination = Pagination()
)