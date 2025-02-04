package com.example.electiva_libre.utils

sealed class ApiResult<T>(var data:T?=null, var error:String?=null){
    class Success<T>(quotes: T):ApiResult<T>(data = quotes)
    class Error<T>(error: String):ApiResult<T>(error = error)
    class Loading<T>:ApiResult<T>()
}