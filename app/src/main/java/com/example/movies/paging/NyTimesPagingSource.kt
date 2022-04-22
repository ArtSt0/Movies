package com.example.movies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.BuildConfig
import com.example.movies.retrofit.NyTimesRepository
import com.example.movies.retrofit.models.MovieModel
import retrofit2.HttpException
import java.io.IOException

class NyTimesPagingSource(
    private val nyTimesRepository: NyTimesRepository
): PagingSource<Int, MovieModel>(){

    //ключ обновления используется для последующих вызовов
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageNumber = params.key ?: 0
        //смешение
        val offset = 20
        return try {

            val response = nyTimesRepository.getMovies(offset = pageNumber, apiKey = BuildConfig.API_KEY)

            var res = listOf<MovieModel>()

            if (response.isSuccessful){
                val results=response.body()?.results
                results?.let {
                    res = it
                }
            }

            val prevKey:Int? = if(pageNumber == 0) { null }else { pageNumber-offset }
            val nextKey:Int? = if (res.isNotEmpty()){ pageNumber+offset }else{ null }

            LoadResult.Page(
                data = res,
                prevKey = prevKey,
                nextKey = nextKey,
            )

        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}
