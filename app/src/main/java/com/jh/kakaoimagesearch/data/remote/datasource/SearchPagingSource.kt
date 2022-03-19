package com.jh.kakaoimagesearch.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jh.kakaoimagesearch.data.remote.response.Document

class SearchPagingSource(private val searchDataSource: SearchDataSource, private val searchString: String) : PagingSource<Int, Document>() {

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1) ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        return kotlin.runCatching {
            val page = params.key ?: 1
            val size = if (params.loadSize >= 80) 80 else params.loadSize
            val apiCall = searchDataSource.getSearchResult(searchString, size, page)
            val loadData = apiCall.documents
            LoadResult.Page(
                data = loadData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (apiCall.meta.is_end) null else page + 1
            )
        }.onFailure {
            return LoadResult.Error(it)
        }.getOrThrow()
    }

}