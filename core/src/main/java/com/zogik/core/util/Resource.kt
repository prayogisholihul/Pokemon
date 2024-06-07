package com.zogik.core.util

sealed class Resource<T>(val data: T? = null, val msg: String? = null) {
    class Init<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    class Success<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(msg: String, data: T? = null) : Resource<T>(msg = msg, data = data)
    class Empty<T> : Resource<T>()
}
