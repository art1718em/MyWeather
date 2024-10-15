package com.example.likes.domain

import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeInteractor
import com.example.likes.api.isLiked
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class LikeInteractorImpl @Inject constructor(
    private val likeCenter: LikeCenter,
) : LikeInteractor {

    override fun getLikeCityFlow(cityName: String): Flow<isLiked> {
        return likeCenter.likes
            .filter { it.cityName == cityName }
            .map { it.isLiked }
    }
}