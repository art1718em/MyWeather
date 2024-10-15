package com.example.likes.domain

import com.example.database.api.CitiesDBManager
import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LikeCenterImpl @Inject constructor(
    private val citiesDBManager: CitiesDBManager,
): LikeCenter {

    private val _likes = MutableSharedFlow<LikeStatus>()
    override val likes = _likes.asSharedFlow()

    override suspend fun setLikeStatus(likeStatus: LikeStatus) {
        withContext(Dispatchers.IO){
            _likes.emit(likeStatus)
        }

        if (likeStatus.isLiked){
            citiesDBManager.addCity(likeStatus.cityName)
        } else {
            citiesDBManager.deleteCity(likeStatus.cityName)
        }
    }
}