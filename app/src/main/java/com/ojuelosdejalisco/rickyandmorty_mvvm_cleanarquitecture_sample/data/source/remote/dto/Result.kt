package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.dto

data class Result(
    val created: String?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val name: String?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)