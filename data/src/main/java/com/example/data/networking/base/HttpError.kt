package com.example.data.networking.base

data class HttpError(val error: Throwable?, val statusCode: Int = 0)