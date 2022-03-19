package com.jh.kakaoimagesearch.data.remote.datasource

import com.jh.kakaoimagesearch.data.remote.Resource
import com.jh.kakaoimagesearch.data.remote.response.SearchResult

interface SearchDataSource {
    suspend fun getSearchResult(searchString: String): Resource<SearchResult>
}