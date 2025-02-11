package com.example.electiva_libre.presentacion.ui.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electiva_libre.R
import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.params.ParamsRegister
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.data.network.models.responses.User
import com.example.electiva_libre.domain.UserUC
import com.example.electiva_libre.presentacion.ui.register.components.StateRegister
import com.example.electiva_libre.utils.ApiResult
import com.example.electiva_libre.utils.isOnline
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterVIewModel @Inject constructor(
    private val useCase: UserUC,
    @ApplicationContext private val context: Context
): ViewModel()  {

    var state by mutableStateOf(StateRegister())
        private set

    var registerResponse by mutableStateOf<ApiResult<User>?>(null)


    fun validate() {
        var isValid = true

        if (state.username.isEmpty()) {
            state = state.copy(errMsgUsername = context.getString(R.string.username_is_required))
            isValid = false
        }
        else if (state.password.isEmpty()) {
            state = state.copy(errMsgUsername = "")
            state = state.copy(errMsgPassword = context.getString(R.string.password_is_required))
            isValid = false
        }
        else if (state.email.isEmpty()) {
            state = state.copy(errMsgPassword = "")
            state = state.copy(errMsgEmail = context.getString(R.string.email_is_required))
            isValid = false
        }
        else if (state.name.isEmpty()) {
            state = state.copy(errMsgEmail = "")
            state = state.copy(errMsgName = context.getString(R.string.name_is_required))
            isValid = false
        } else if (state.lastname.isEmpty()) {
            state = state.copy(errMsgName = "")
            state = state.copy(errMsgLastname = context.getString(R.string.lastname_is_required))
            isValid = false
        } else if (state.password2.isEmpty()) {
            state = state.copy(errMsgLastname = "")
            state = state.copy(errMsgPassword2 = context.getString(R.string.password_confirmation_required))
            isValid = false
        } else if (state.password2 != state.password) {
            state = state.copy(errMsgPassword2 = context.getString(R.string.passwords_do_not_match))
            isValid = false
        }
        if (isValid) {
            register()
            state = state.copy(errMsgPassword2 = "")
        }
    }

    fun setTextUsername(text: String) {
        state = state.copy(username = text)
    }

    fun setTextPassword(text: String) {
        state = state.copy(password = text)
    }

    fun setTextEmail(text: String) {
        state = state.copy(email = text)
    }

    fun setTextName(text: String) {
        state = state.copy(name = text)
    }

    fun setTextLastname(text: String) {
        state = state.copy(lastname = text)
    }

    fun setTextPassword2(text: String) {
        state = state.copy(password2 = text)
    }


    private fun register()  = viewModelScope.launch {
        if (isOnline(context)) useCase.register(params = ParamsRegister(
            username = state.username,
            email = state.email,
            firstName = state.name,
            lastName = state.lastname,
            password = state.password
        )
        ).collect{
            registerResponse = it
        } else registerResponse= ApiResult.Error(context.getString(R.string.Failed))
    }
}