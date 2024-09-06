package com.baims.dailyforcast.domain.utils

sealed class DataState<out R> {
    data class Success<out T>(val isFromCache: Boolean = false, val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}