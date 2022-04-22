package com.example.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.paging.MovieRecyclerViewAdapter
import com.example.movies.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vmMain: MainViewModel by viewModels()
    private lateinit var bind: ActivityMainBinding
    private lateinit var pagingAdapter: MovieRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.vmMain = vmMain
        bind.lifecycleOwner = this

        bind.rvPersons.layoutManager = LinearLayoutManager(this)
        pagingAdapter = MovieRecyclerViewAdapter()
        bind.rvPersons.adapter = pagingAdapter

        lifecycleScope.launch {
            vmMain.getMoviesFlow().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }
}