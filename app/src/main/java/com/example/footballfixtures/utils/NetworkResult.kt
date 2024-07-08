package com.example.footballfixtures.utils

sealed class NetworkResult<T : Any>(val data: T?=null,val message: String?=null) {
    class Success<T: Any>( data: T) : NetworkResult<T>()
    class Error<T: Any>( message: String?, data: T?=null) : NetworkResult<T>(data,message)
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}