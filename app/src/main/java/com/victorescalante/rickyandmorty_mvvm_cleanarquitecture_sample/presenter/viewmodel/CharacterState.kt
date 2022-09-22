package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.viewmodel

import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.domain.model.CharacterModel

data class CharacterState(
    val ok: List<CharacterModel> = emptyList(),
    val fail: String = "",
    val loading: Boolean = false
)
