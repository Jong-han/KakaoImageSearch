package com.jh.kakaoimagesearch.data.remote.datasource

import com.jh.kakaoimagesearch.data.remote.Resource
import com.jh.kakaoimagesearch.data.remote.response.SearchResult
import com.jh.kakaoimagesearch.data.remote.service.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val service: SearchService): SearchDataSource {
    override suspend fun getSearchResult(searchString: String): Resource<SearchResult> {
        return kotlin.runCatching {
            val apiCall = service.getSearchResult(searchString = searchString)
            Resource.Success(apiCall)
        }.onFailure {
            return Resource.Error(it.message)
        }.getOrThrow()
    }
}