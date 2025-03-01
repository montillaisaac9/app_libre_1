package com.example.electiva_libre.presentacion.ui.courses

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electiva_libre.R
import com.example.electiva_libre.data.network.models.responses.ResponseListCourses
import com.example.electiva_libre.domain.DataUC
import com.example.electiva_libre.domain.UserUC
import com.example.electiva_libre.utils.ApiResult
import com.example.electiva_libre.utils.isOnline
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesVieModel @Inject constructor(
    private val useCase: UserUC,
    private val dataCase: DataUC,
    @ApplicationContext private val context: Context
): ViewModel() {

    var CoursesResponse by mutableStateOf<ApiResult<ResponseListCourses>?>(null)
        private set

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        if (isOnline(context)) dataCase.getCourses().collect{
            CoursesResponse = it
        } else CoursesResponse= ApiResult.Error(context.getString(R.string.Failed))
    }
}