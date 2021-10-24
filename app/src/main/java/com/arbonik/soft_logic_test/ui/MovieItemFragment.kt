package com.arbonik.soft_logic_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.arbonik.soft_logic_test.R
import com.arbonik.soft_logic_test.databinding.MovieItemBinding
import com.arbonik.soft_logic_test.databinding.MovieItemFragmentBinding
import com.arbonik.soft_logic_test.utils.Resource
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MovieItemFragment @Inject constructor() : Fragment() {

    private lateinit var binding : MovieItemFragmentBinding
    private val viewModel: MovieItemViewModel by hiltNavGraphViewModels(R.navigation.navigation_main)
    private val args : MovieItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.moviesId?.let {
            viewModel.loadMovie(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieItemFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.coroutineScope.launchWhenStarted {
            viewModel.movieState.collectLatest {
                when(it){
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.errorTextView.text = it.message
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE

                        Glide.with(view).load("https://" + it.data!!.poster).into(binding.image)
                        binding.title.text = it.data.title
                        binding.description.text = it.data.description
                    }
                }
            }
        }
    }
}