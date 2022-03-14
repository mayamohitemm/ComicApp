package com.example.comicapp.util

sealed class UiState<out R> {
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
}
