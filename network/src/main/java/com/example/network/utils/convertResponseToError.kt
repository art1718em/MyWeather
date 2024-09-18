package com.example.network.utils

import com.example.model.CoreError
import com.example.model.RootError
import retrofit2.Response

fun <T> Response<out T>.convertCodeToProfileError(): RootError {
    return when (this.code()) {
        404 -> CoreError.NOT_FOUND
        else -> CoreError.UNKNOWN_ERROR
    }
}