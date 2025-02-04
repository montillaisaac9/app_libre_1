package com.example.electiva_libre.data.networks.response

class BaseResponse<T>(
    val statusCode: Int = 200,
    val data : T,
    val message : String
)