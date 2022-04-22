package com.example.movies.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.paging.NyTimesPagingSource
import com.example.movies.retrofit.NyTimesRepository
import com.example.movies.retrofit.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val nyTimesRepository: NyTimesRepository
): ViewModel(){

    fun getMoviesFlow(): Flow<PagingData<MovieModel>> {
        return Pager(getDefaultPageConfig()){
            NyTimesPagingSource(nyTimesRepository)
        }.flow.cachedIn(viewModelScope)
    }
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20)
    }
}