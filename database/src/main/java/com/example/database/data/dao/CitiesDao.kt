package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.database.data.dbo.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CitiesDao {

    @Insert
    fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM favourite_cities")
    fun getAll(): Flow<List<CityEntity>>

    @Query("DELETE FROM favourite_cities WHERE name = :cityName")
    fun deleteCity(cityName: String)

    @Query("UPDATE favourite_cities SET isSelected = :isSelected WHERE name = :cityName")
    fun changeIsSelected(cityName: String, isSelected: Boolean)

    @Query("SELECT name FROM favourite_cities WHERE isSelected = 1")
    fun getSelectedCity(): Flow<String?>

}