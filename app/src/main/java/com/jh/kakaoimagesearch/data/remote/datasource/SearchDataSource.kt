package com.jh.kakaoimagesearch.data.remote.datasource

import com.jh.kakaoimagesearch.data.remote.response.SearchResult

interface SearchDataSource {
    suspend fun getSearchResult(searchString: String, size: Int, page: Int): SearchResult
}