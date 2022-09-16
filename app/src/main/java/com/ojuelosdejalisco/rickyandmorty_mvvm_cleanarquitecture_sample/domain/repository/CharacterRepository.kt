package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.repository

import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity.CharacterEntity
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.dto.Character

interface CharacterRepository {
    suspend fun getCharacter(): Character

    suspend fun characterInsert(data: List<CharacterEntity>)
    suspend fun characterDelete()
    suspend fun characterSelect(): List<CharacterEntity>
}