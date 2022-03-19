package com.jh.kakaoimagesearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jh.kakaoimagesearch.data.remote.datasource.SearchDataSource
import com.jh.kakaoimagesearch.data.remote.datasource.SearchPagingSource
import com.jh.kakaoimagesearch.data.remote.response.Document
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val dataSource: SearchDataSource): RemoteRepository {
    override fun getSearchResult(searchString: String): Flow<PagingData<Document>> = Pager(
        PagingConfig(pageSize = 30)
    ) {
        SearchPagingSource(dataSource, searchString)
    }.flow
}