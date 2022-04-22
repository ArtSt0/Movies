package com.example.movies.retrofit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieModel (
    @SerializedName("display_title")
    val displayTitle:String?="",
    @SerializedName("mpaa_rating")
    val mpaaRating:String?="",
    @SerializedName("critics_pick")
    val criticsPick:String?="",
    @SerializedName("byline")
    val byLine:String?="",
    @SerializedName("headline")
    val headLine:String?="",
    @SerializedName("summary_short")
    val summaryShort:String?="",
    @SerializedName("publication_date")
    val publicationDate:String?="",
    @SerializedName("opening_date")
    val openingDate:String?="",
    @SerializedName("date_updated")
    val dateUpdated:String?="",
    @SerializedName("link")
    val link:Any?="",
    @SerializedName("multimedia")
    val multimedia:MultiMediaModel?,
):Serializable