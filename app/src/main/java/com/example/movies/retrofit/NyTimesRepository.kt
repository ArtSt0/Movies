package com.example.movies.retrofit

import javax.inject.Inject

class NyTimesRepository @Inject constructor(
    private val retrofitApiInterface: RetrofitApiInterface
){
    suspend fun getMovies(offset:Int, apiKey:String) = retrofitApiInterface.getMovies(offset = offset, order = "by-publication-date", apiKey = apiKey)
}
