package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.domain.usecase.CharacterUseCase
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val useCase: CharacterUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        useCase().collect{
            when(it){
                is Resource.Error ->  _state.value = CharacterState(fail=it.message?:"error")
                is Resource.Loading -> _state.value = CharacterState(loading = true)
                is Resource.Success -> _state.value = CharacterState(ok=it.data ?: emptyList())
            }
        }
    }
}