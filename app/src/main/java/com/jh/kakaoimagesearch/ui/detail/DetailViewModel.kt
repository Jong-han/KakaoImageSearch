package com.jh.kakaoimagesearch.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jh.kakaoimagesearch.data.remote.response.Document
import com.jh.kakaoimagesearch.ui.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val document = savedStateHandle.get<Document>(MainActivity.EXTRA_DOCUMENT)
}