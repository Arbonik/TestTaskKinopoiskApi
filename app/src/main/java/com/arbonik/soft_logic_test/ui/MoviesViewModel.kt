package com.arbonik.soft_logic_test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbonik.soft_logic_test.data.allMovies.MoviesPage
import com.arbonik.soft_logic_test.data.utils.Resource
import com.arbonik.soft_logic_test.network.KinopoiskReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val kinopoiskReference: KinopoiskReference
) : ViewModel() {

    private val _pageLoad: MutableStateFlow<Resource<MoviesPage>> =
        MutableStateFlow(Resource.Loading())
    val pageLoad: StateFlow<Resource<MoviesPage>> = _pageLoad

    fun loadFilms() {
        _pageLoad.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = kinopoiskReference.getPage().execute()
            if (response.isSuccessful) {
                _pageLoad.value = Resource.Success(response.body()!!)
            } else {
                _pageLoad.value = Resource.Error(response.message())
            }
        }
    }
}