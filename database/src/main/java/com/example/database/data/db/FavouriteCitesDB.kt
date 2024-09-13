package com.example.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.dao.CitiesDao
import com.example.database.data.dbo.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
internal abstract class FavouriteCitesDB : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao
}