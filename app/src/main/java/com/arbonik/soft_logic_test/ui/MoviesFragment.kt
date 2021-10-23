package com.arbonik.soft_logic_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arbonik.soft_logic_test.R
import com.arbonik.soft_logic_test.data.utils.Resource
import com.arbonik.soft_logic_test.databinding.MoviesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by hiltNavGraphViewModels(R.id.navigation_main)

    private lateinit var binding: MoviesFragmentBinding

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MoviesAdapter(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.button.setOnClickListener {
            viewModel.loadFilms()
        }
        binding.listView.adapter = adapter
        binding.listView.layoutManager = LinearLayoutManager(requireContext())
        lifecycle.coroutineScope.launchWhenStarted {
            viewModel.pageLoad.collectLatest(adapter::submitData)
        }
//        lifecycle.coroutineScope.launchWhenStarted {
//            viewModel.pageLoad.collectLatest {
//                when (it) {
//                    is Resource.Error -> {
//                        binding.progressBar.visibility = View.GONE
//                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG).show()
//                    }
//                    is Resource.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
//                    }
//                    is Resource.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.listView.adapter = ArrayAdapter(
//                            requireContext(),
//                            android.R.layout.simple_list_item_1,
//                            it.data!!.movies
//                        )
//                    }
//                }
//            }
//        }
    }
}