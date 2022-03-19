package com.jh.kakaoimagesearch.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.jh.kakaoimagesearch.data.remote.response.Document
import com.jh.kakaoimagesearch.di.IoDispatcher
import com.jh.kakaoimagesearch.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchUseCase: SearchUseCase,
                                        @IoDispatcher private val ioDispatcher: CoroutineDispatcher): ViewModel() {

    val searchString = MutableStateFlow("")

    private val _pagingData = MutableStateFlow<PagingData<Document>>(PagingData.empty())
    val pagingData: StateFlow<PagingData<Document>> = _pagingData

    fun getSearchResult(searchString: String) {
        if (searchString.isNotEmpty()) {
            viewModelScope.launch(ioDispatcher) {
                searchUseCase.getSearchResult(searchString).collectLatest {
                    _pagingData.value = it
                }
            }
        }
    }

}