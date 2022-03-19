package com.jh.kakaoimagesearch.data.remote.datasource

import com.jh.kakaoimagesearch.data.remote.response.SearchResult
import com.jh.kakaoimagesearch.data.remote.service.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val service: SearchService): SearchDataSource {
    override suspend fun getSearchResult(searchString: String, size: Int, page: Int): SearchResult {
        return service.getSearchResult(searchString = searchString, size = size, page = page)
    }
}