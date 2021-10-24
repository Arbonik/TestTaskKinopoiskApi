package com.arbonik.soft_logic_test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbonik.soft_logic_test.data.allMovies.Movy
import com.arbonik.soft_logic_test.network.MoviesReference
import com.arbonik.soft_logic_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieItemViewModel @Inject constructor(
    private val moviesReference: MoviesReference
) : ViewModel() {

    private val _movieState: MutableStateFlow<Resource<Movy>> = MutableStateFlow(Resource.Loading())
    val movieState: StateFlow<Resource<Movy>> = _movieState

    fun loadMovie(movieId: String) {
        _movieState.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesReference.getMovie(movieId)
            if (response.isSuccessful) {
                _movieState.value = Resource.Success(response.body()!!)
            } else {
                _movieState.value = Resource.Error(response.message())
            }
        }
    }
}