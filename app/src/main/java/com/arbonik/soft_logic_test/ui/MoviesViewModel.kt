package com.arbonik.soft_logic_test.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arbonik.soft_logic_test.MoviesPageSource
import com.arbonik.soft_logic_test.data.allMovies.Movy
import com.arbonik.soft_logic_test.network.KinopoiskReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
//    val kinopoiskReference: KinopoiskReference,
    val pagingSource: MoviesPageSource
) : ViewModel() {

    //    private val _pageLoad: MutableStateFlow<PagingData<MoviesPage>> =
//        MutableStateFlow()
    val pageLoad: StateFlow<PagingData<Movy>> = Pager(PagingConfig(pageSize = 5)) {
        pagingSource
    }.flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun loadFilms() {
//        _pageLoad.value = Resource.Loading()
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = kinopoiskReference.getPage()
//            if (response.isSuccessful) {
//                _pageLoad.value = Resource.Success(response.body()!!)
//            } else {
//                _pageLoad.value = Resource.Error(response.message())
//            }
//        }
    }
}