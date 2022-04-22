package com.example.movies.retrofit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesModel(
    val status: Any?,
    val copyright: Any?,
    @SerializedName("has_more")
    val hasMore: Any?,
    @SerializedName("num_results")
    val numResults: Any?,
    val results: List<MovieModel>?,
):Serializable
