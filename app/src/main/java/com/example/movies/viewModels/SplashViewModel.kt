package com.example.movies.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashViewModel: ViewModel(){

    //задержка по времени c возвратом результата
    fun getIsReady(): Boolean {
        val result = viewModelScope.runCatching {
            runBlocking {
                delay(1000)
            }
            true
        }
        return result.isSuccess
    }
}