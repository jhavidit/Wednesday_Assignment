package tech.jhavidit.wednesdayassignment.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tech.jhavidit.wednesdayassignment.models.MusicItem


interface APIInterface {
    @GET("search")
    fun getCountryList(
        @Query("term") searchTerm: String
    ): Call<MusicItem>
}