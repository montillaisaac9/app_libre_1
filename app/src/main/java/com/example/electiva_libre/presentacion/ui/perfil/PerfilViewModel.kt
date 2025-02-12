package com.example.electiva_libre.presentacion.ui.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.domain.UserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val useCase: UserUC
): ViewModel(){

    var user: UserEntity = UserEntity()

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user = useCase.getLocalUser().first()
    }

    fun logout() = viewModelScope.launch { useCase.deleteUserLocale() }
}