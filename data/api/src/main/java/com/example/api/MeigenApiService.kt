package com.example.api

import com.example.model.Meigen

interface MeigenApiService {
    suspend fun getMeigenList(): List<Meigen>
}