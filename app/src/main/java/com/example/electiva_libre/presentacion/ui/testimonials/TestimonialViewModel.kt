package com.example.electiva_libre.presentacion.ui.testimonials

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electiva_libre.R
import com.example.electiva_libre.data.network.models.params.ParamTestimonialAdd
import com.example.electiva_libre.data.network.models.params.ParamsRegister
import com.example.electiva_libre.data.network.models.responses.ResponseListNews
import com.example.electiva_libre.data.network.models.responses.ResponseListTestimonials
import com.example.electiva_libre.data.network.models.responses.User
import com.example.electiva_libre.domain.DataUC
import com.example.electiva_libre.domain.UserUC
import com.example.electiva_libre.utils.ApiResult
import com.example.electiva_libre.utils.isOnline
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestimonialViewModel @Inject constructor(
    private val useCase: UserUC,
    private val dataCase: DataUC,
    @ApplicationContext private val context: Context
): ViewModel() {

    var testimonialResponse by mutableStateOf<ApiResult<ResponseListTestimonials>?>(null)
        private set


    var newTestimonial by mutableStateOf("")
    var newErrTestimonial by mutableStateOf("")

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        if (isOnline(context)) dataCase.getTestimonials().collect {
            testimonialResponse = it
        } else testimonialResponse = ApiResult.Error(context.getString(R.string.Failed))
    }

    fun validate() {
        if (newTestimonial.isEmpty()) newErrTestimonial = "el testimonio no puede estar vacio"
        else {
            newErrTestimonial = ""
            postTestimonial()
        }
    }

    private fun postTestimonial() = viewModelScope.launch {
        val user = useCase.getLocalUser().first()

        if (isOnline(context)) dataCase.postTestimonials(ParamTestimonialAdd(
            author = user.id,
            content = newTestimonial,
            isApproved = false
            )
        ).collect { when(it){
                 is ApiResult.Error -> testimonialResponse = ApiResult.Error(context.getString(R.string.Failed))
                 is ApiResult.Loading -> testimonialResponse = ApiResult.Loading()
                 is ApiResult.Success -> getNews()
                 null -> {}
        }
        } else testimonialResponse = ApiResult.Error(context.getString(R.string.Failed))
    }
}

