package com.example.database.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.CityWeather

@Entity(tableName = "favourite_cities")
internal data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("isSelected") val isSelected: Boolean,
)

internal fun CityEntity.toCityWeather() : CityWeather{
    return CityWeather(
        name = name,
        description = null,
        temperature = null,
        temperatureFeelsLike = null,
        humidity = null,
        pressure = null,
    )
}