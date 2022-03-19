package com.jh.kakaoimagesearch.ui.main

import androidx.lifecycle.ViewModel
import com.jh.kakaoimagesearch.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchUseCase: SearchUseCase): ViewModel() {

    fun getSearchResult() = searchUseCase.getSearchResult("apple")

}