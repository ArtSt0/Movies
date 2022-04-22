package com.example.movies.retrofit

import com.example.movies.retrofit.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiInterface {
    /*
        offset - смешение
        order - сортировка (by-opening-date, by-publication-date)
        api-key - ключ доступа
    */
    @GET("svc/movies/v2/reviews/all.json")
    suspend fun getMovies(@Query("offset") offset:Int, @Query("order") order:String, @Query("api-key") apiKey:String): Response<MoviesModel>
}
