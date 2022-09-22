package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.repository

import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.CharacterDao
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity.CharacterEntity
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.ApiService
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.dto.Character
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val characterDao: CharacterDao
) : CharacterRepository {
    override suspend fun getCharacter(): Character = apiService.getCharacter()

    override suspend fun characterInsert(data: List<CharacterEntity>) =
        characterDao.characterInsert(data)

    override suspend fun characterDelete() = characterDao.characterDelete()
    override suspend fun characterSelect(): List<CharacterEntity> = characterDao.characterSelect()
}