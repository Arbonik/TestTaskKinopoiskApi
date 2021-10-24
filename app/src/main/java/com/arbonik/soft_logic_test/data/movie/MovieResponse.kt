package com.arbonik.soft_logic_test.data.movie

import com.arbonik.soft_logic_test.data.allMovies.Collapse
import com.arbonik.soft_logic_test.data.allMovies.Hdvb
import com.arbonik.soft_logic_test.data.allMovies.Kodik
import com.arbonik.soft_logic_test.data.allMovies.Videocdn

data class MovieResponse(
    val actors: List<String> = listOf(),
    val age: String = "",
    val budget: String? = "",
    val collapse: Collapse = Collapse(),
    val composers: List<String>? = listOf(),
    val countries: List<String>? = listOf(),
    val description: String? = "",
    val directors: List<String>? = listOf(),
    val editors: List<String>? = listOf(),
    val fees_russia: String? = "",
    val fees_world: String? = "",
    val frames: List<String> = listOf(),
    val genres: List<String> = listOf(),
    val hdvb: Hdvb = Hdvb(),
    val id: Int = 0,
    val id_kinopoisk: Int = 0,
    val imdb_votes: String? = "",
    val kinopoisk_votes: String? = "",
    val kodik: Kodik = Kodik(),
    val operators: List<String>? = listOf(),
    val painters: List<String>? = listOf(),
    val poster: String = "",
    val premiere_russia: String = "",
    val premiere_world: String = "",
    val producers: List<String>? = listOf(),
    val rating_imdb: Double = 0.0,
    val rating_kinopoisk: Double = 0.0,
    val screenshots: List<String>? = null,
    val screenwriters: List<String> = listOf(),
    val tagline: String = "",
    val title: String = "",
    val title_alternative: String? = "",
    val trailer: String = "",
    val type: String = "",
    val url: String = "",
    val videocdn: Videocdn = Videocdn(),
    val year: Int = 0
)