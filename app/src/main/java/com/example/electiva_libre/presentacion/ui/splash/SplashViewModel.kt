package com.example.electiva_libre.presentacion.ui.splash



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.domain.UserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val useCase: UserUC,
    private val defaultDispatcher: CoroutineDispatcher
): ViewModel()  {

    var responseDataBase by mutableStateOf<UserEntity?>(null)
        private set

    fun getUserDb() = viewModelScope.launch {
        useCase.getLocalUser().collect{
            responseDataBase = it
        }
    }

}