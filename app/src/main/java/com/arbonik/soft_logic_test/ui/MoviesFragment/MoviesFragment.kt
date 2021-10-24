package com.arbonik.soft_logic_test.ui.MoviesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.arbonik.soft_logic_test.R
import com.arbonik.soft_logic_test.databinding.MoviesFragmentBinding
import com.arbonik.soft_logic_test.ui.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by hiltNavGraphViewModels(R.id.navigation_main)
    private lateinit var binding: MoviesFragmentBinding

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MoviesAdapter(requireContext()) {
            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMovieItemFragment(it)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.adapter = adapter
        binding.listView.layoutManager = LinearLayoutManager(requireContext())
        lifecycle.coroutineScope.launchWhenStarted {
            viewModel.pageLoad.collectLatest(adapter::submitData)
        }
        adapter.addLoadStateListener { combinedLoadStates ->
            binding.progressBar.isVisible = combinedLoadStates.refresh == LoadState.Loading
        }
    }
}