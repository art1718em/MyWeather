package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.CitiesDBManagerImpl
import com.example.database.api.CitiesDBManager
import com.example.database.data.db.FavouriteCitesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    internal fun provideCitiesDBManager(@ApplicationContext context: Context): CitiesDBManager{
        return CitiesDBManagerImpl(
            Room.databaseBuilder(
                context = context,
                klass = FavouriteCitesDB::class.java,
                name = "favourite_cities",
            ).build()
        )
    }
}