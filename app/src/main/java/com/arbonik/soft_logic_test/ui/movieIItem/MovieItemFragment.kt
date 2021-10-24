package com.arbonik.soft_logic_test.ui.movieIItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.arbonik.soft_logic_test.R
import com.arbonik.soft_logic_test.databinding.MovieItemFragmentBinding
import com.arbonik.soft_logic_test.utils.Resource
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MovieItemFragment @Inject constructor() : Fragment() {

    private lateinit var binding: MovieItemFragmentBinding
    private val viewModel: MovieItemViewModel by hiltNavGraphViewModels(R.id.navigation_main)
    private val args: MovieItemFragmentArgs by navArgs()

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
        initMovieObserver(view)
    }

    private fun initMovieObserver(
        view: View
    ) {
        lifecycle.coroutineScope.launchWhenStarted {
            viewModel.movieState.collectLatest {
                when (it) {
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.errorTextView.text = it.message
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        if (it.data != null) {
                            val item = it.data
                            with(binding) {
                                progressBar.visibility = View.GONE
                                ratingBar.rating = item.rating_kinopoisk.toFloat()
                                actorsTextView.text = item.actors.joinToString()
                                item.premiere_world?.let { prew ->
                                    getString(R.string.worldPremier) + prew
                                }
                                item.premiere_russia?.let { prew ->
                                    getString(R.string.russiaPreview) + prew
                                }
                                item.countries?.let {
                                    countiesTextView.text = it.joinToString()
                                }
                                ratingBar.rating = item.rating_kinopoisk.toFloat()
                                Glide.with(view).load("https://" + item.poster)
                                    .into(binding.image)
                                title.text = item.title
                                description.text = item.description
                            }
                        }
                    }
                }
            }
        }
    }
}