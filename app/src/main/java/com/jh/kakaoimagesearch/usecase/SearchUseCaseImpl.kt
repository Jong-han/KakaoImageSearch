package com.jh.kakaoimagesearch.usecase

import androidx.paging.PagingData
import com.jh.kakaoimagesearch.data.remote.response.Document
import com.jh.kakaoimagesearch.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val remoteRepository: RemoteRepository): SearchUseCase {
    override fun getSearchResult(searchString: String): Flow<PagingData<Document>> {
        return remoteRepository.getSearchResult(searchString)
    }
}