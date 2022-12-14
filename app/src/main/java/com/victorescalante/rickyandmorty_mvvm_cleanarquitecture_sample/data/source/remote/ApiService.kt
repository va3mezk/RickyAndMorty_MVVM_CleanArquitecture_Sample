package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote

import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.dto.Character
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.util.Constants
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getCharacter(): Character
}