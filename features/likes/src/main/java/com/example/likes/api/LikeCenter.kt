package com.example.likes.api

import kotlinx.coroutines.flow.Flow

interface LikeCenter {

    val likes: Flow<LikeStatus>

    suspend fun setLikeStatus(likeStatus: LikeStatus)
}