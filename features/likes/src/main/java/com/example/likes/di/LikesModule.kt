package com.example.likes.di

import com.example.likes.api.LikeCenter
import com.example.likes.api.LikeInteractor
import com.example.likes.domain.LikeCenterImpl
import com.example.likes.domain.LikeInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LikesModule {

    @Singleton
    @Binds
    abstract fun provideLikeCenter(likeCenterImpl: LikeCenterImpl): LikeCenter

    @Binds
    abstract fun provideLikeInteractor(likeInteractorImpl: LikeInteractorImpl): LikeInteractor
}