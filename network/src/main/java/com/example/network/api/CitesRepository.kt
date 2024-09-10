package com.example.network.api

interface CitesRepository {
    fun getCity(): Result<List<Int>>
}