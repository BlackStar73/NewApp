package com.example.newapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newapp.R
import com.example.newapp.databinding.FragmentHomeBinding
import com.example.newapp.ui.MovieAdapter
import com.example.newapp.utils.collectLA
import com.example.newapp.utils.isOnline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = MovieAdapter()
            rv.adapter = adapter

            viewModel.onEvent(HomeScreenUiEvent.GetMovies)

            /*viewModel.connectionStatus.collectLA(viewLifecycleOwner) { status ->
                when(status.isOnline) {
                    true -> {
                        viewModel.onEvent(HomeScreenUiEvent.GetMovies)

                        *//*search.addTextChangedListener {
                            if (search.text.toString() == "") {
                                viewModel.onEvent(HomeScreenUiEvent.GetNews)
                            } else {
                                submitSearch.setOnClickListener {
                                    val search = search.text.toString()
                                    viewModel.onEvent(HomeScreenUiEvent.SearchNews(search))
                                }
                            }
                        }*//*
                    }

                    false -> {
                        Toast.makeText(
                            context,
                            "Check your internet connection!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }*/

            adapter?.setOnClickListener { article ->
                findNavController().navigate(
                    R.id.action_homeFragment_to_articleFragment,
                    bundleOf("article" to article)
                )
            }

            viewModel.
            uiState.collectLA(viewLifecycleOwner) { state ->
                /*progressBar.isVisible = state.loading
                llEmpty.isVisible = state.isEmpty*/

                if (state.error != null) {
                    Toast.makeText(requireContext(), "${state.error}", Toast.LENGTH_SHORT)
                        .show()
                }

                adapter?.submitList(state.data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}