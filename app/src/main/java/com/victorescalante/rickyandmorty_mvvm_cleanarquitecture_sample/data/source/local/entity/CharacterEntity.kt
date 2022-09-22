package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.domain.model.CharacterModel


@Entity(tableName = "character")
data class CharacterEntity(
    val created: String?,
    val gender: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val image: String?,
    val name: String?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)

fun CharacterModel.toRoom() = CharacterEntity(
    created = created,
    gender = gender,
    id = id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url
)