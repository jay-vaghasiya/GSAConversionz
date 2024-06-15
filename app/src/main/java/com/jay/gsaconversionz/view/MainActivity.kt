package com.jay.gsaconversionz.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.gsaconversionz.databinding.ActivityMainBinding
import com.jay.gsaconversionz.model.data.Search
import com.jay.gsaconversionz.view.adapter.MoviesPagingAdapter
import com.jay.gsaconversionz.view.pagination.LoaderAdapter
import com.jay.gsaconversionz.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MoviesPagingAdapter.OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MoviesPagingAdapter
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callViewModel()
        setupSearch()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = MoviesPagingAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = LoaderAdapter(),
            header = LoaderAdapter()
        )
    }

    private fun setupSearch() {
        binding.svMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length >= 3) {
                    viewModel.list(newText).observe(this@MainActivity) { pagingData ->
                        adapter.submitData(lifecycle, pagingData)
                    }
                }
                return true
            }
        })
    }


    private fun callViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun OnItemClick(search: Search) {

    }
}