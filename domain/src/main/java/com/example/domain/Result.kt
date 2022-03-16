package com.example.domain

sealed class Result<out R> {
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()
    data class Error(val errorMessage: String) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
}
