package com.example.electiva_libre.presentacion.ui.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electiva_libre.R
import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.domain.UserUC
import com.example.electiva_libre.presentacion.ui.login.components.StateLogin
import com.example.electiva_libre.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: UserUC,
    @ApplicationContext private val context: Context
): ViewModel() {

    var state by mutableStateOf(StateLogin())
        private set

    var logginResponse by mutableStateOf<ApiResult<ResponseLogin>?>(null)



    fun setTextUsername(it: String) {
        state = state.copy(username = it)
    }

    fun setTextPassword(it: String) {
        state = state.copy(password = it)
    }


    fun validate() {
        if (state.username.isEmpty()) {
            state = state.copy(errMsgUsername = context.getString(R.string.username_is_required))
        }else if (state.password.isEmpty()) {
            state = state.copy(errMsgUsername = "")
            state = state.copy(errMsgPassword = context.getString(R.string.password_case_use_1))
        } else {
            state = state.copy(errMsgPassword = "")
            state = state.copy(errMsgUsername = "")
            login()
        }

    }

    fun login() = viewModelScope.launch {
         useCase.login(params = ParamsLogin(
            username = state.username,
            password = state.password
        )).collect{
             logginResponse = it
         }
    }

    fun inserUser(user: UserEntity) = viewModelScope.launch {
        useCase.insertUser(user)
    }



}