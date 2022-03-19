package com.jh.kakaoimagesearch.data.repository

import androidx.paging.PagingData
import com.jh.kakaoimagesearch.data.remote.response.Document
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getSearchResult(searchString: String): Flow<PagingData<Document>>
}