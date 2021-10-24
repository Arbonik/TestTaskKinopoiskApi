package com.arbonik.soft_logic_test.ui.MoviesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arbonik.soft_logic_test.network.MoviesPageSource
import com.arbonik.soft_logic_test.data.allMovies.Movy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val pagingSource: MoviesPageSource
) : ViewModel() {

    val pageLoad: StateFlow<PagingData<Movy>> = Pager(PagingConfig(pageSize = 10)) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}