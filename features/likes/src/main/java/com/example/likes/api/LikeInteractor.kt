package com.example.likes.api

import kotlinx.coroutines.flow.Flow

typealias isLiked = Boolean

interface LikeInteractor {

    fun getLikeCityFlow(cityName: String): Flow<isLiked>

}