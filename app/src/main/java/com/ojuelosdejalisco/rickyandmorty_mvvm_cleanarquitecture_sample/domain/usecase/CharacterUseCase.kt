package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.usecase

import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity.toRoom
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.model.CharacterModel
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.model.toDomain
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.repository.CharacterRepository
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Flow<Resource<List<CharacterModel>>> = flow {
        try {
            emit(Resource.Loading<List<CharacterModel>>())
            val data = repository.characterSelect().map { it.toDomain() }
            if (data.isNotEmpty()) {
                emit(Resource.Success<List<CharacterModel>>(data))
            } else {
                val remote = repository.getCharacter().results!!.map { it!!.toDomain() }
                repository.characterDelete()
                repository.characterInsert(remote.map { it.toRoom() })
                emit(Resource.Success<List<CharacterModel>>(remote))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<CharacterModel>>(e.localizedMessage?: "No internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CharacterModel>>("error"))
        }
    }
}