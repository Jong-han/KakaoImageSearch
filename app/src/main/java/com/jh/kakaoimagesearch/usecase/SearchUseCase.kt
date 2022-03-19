package com.jh.kakaoimagesearch.usecase

import androidx.paging.PagingData
import com.jh.kakaoimagesearch.data.remote.response.Document
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun getSearchResult(searchString: String): Flow<PagingData<Document>>
}