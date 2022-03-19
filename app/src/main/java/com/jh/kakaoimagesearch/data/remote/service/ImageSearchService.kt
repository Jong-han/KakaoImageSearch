package com.jh.kakaoimagesearch.data.remote.service

import com.jh.kakaoimagesearch.data.remote.response.SearchResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {
    @GET("search/image")
    suspend fun getSearchResult(@Header("Authorization") apiKey: String = "KakaoAK 08bc8f13aa488f85b1d1bd31070523cb",
                                @Query("query") searchString: String,
                                @Query("size") size: Int,
                                @Query("page") page: Int): SearchResult
}