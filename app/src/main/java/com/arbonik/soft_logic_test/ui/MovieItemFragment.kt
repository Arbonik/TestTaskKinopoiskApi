package com.arbonik.soft_logic_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.arbonik.soft_logic_test.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieItemFragment @Inject constructor() : Fragment() {

    private val viewModel: MovieItemViewModel by hiltNavGraphViewModels(R.navigation.navigation_main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_item_fragment, container, false)
    }

}