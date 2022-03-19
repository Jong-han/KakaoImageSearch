package com.jh.kakaoimagesearch.data.remote.response

data class SearchResult(
    val documents: List<Document>,
    val meta: Meta
)