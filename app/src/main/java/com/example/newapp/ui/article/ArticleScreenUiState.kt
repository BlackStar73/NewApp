package com.example.newapp.ui.article

import com.example.newapp.data.model.Article

data class ArticleScreenUiState(
    val loading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val data: List<Article?>? = emptyList(),
    val isOnline: Boolean? = null
)
