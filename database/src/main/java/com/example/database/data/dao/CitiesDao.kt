package com.example.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
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

    @Query("UPDATE favourite_cities SET isSelected = 1 WHERE name = :cityName")
    fun selectCity(cityName: String)

    @Query("UPDATE favourite_cities SET isSelected = 0 WHERE isSelected = 1")
    fun unselectCity()

    @Transaction
    fun updateSelectedCity(cityName: String){
        unselectCity()
        selectCity(cityName)
    }

    @Query("SELECT name FROM favourite_cities WHERE isSelected = 1")
    fun getSelectedCity(): Flow<String?>

    @Query("SELECT * FROM favourite_cities ORDER BY id ASC LIMIT 1")
    fun getFirstItem(): CityEntity?

    @Query("SELECT COUNT(*) FROM favourite_cities WHERE name = :cityName")
    suspend fun isCityExists(cityName: String): Int
}