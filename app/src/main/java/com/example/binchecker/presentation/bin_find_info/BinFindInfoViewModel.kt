package com.example.binchecker.presentation.bin_find_info

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.data.BinListApi
import com.example.binchecker.data.BinResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class UiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val binField: String = "",
)



@HiltViewModel
class BinFindInfoViewModel @Inject constructor(
    private val api: BinListApi
) : ViewModel() {

    private lateinit var _binState: BinResponse

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun sendBin(bin: String){
        _uiState.value = _uiState.value.copy(
            binField = bin
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                fetchData(bin)
            }
        }
    }

    private suspend fun fetchData(bin: String): Boolean {
        if (checkRequirements(bin)){
            val fetchResult: BinResponse = api.getBinDetails(bin = bin)
            _binState = fetchResult
            return true
        }
        else {
            return false
        }
    }

    private fun checkRequirements(bin: String): Boolean {
        if (requiredLength(bin) && digitsOnly(bin)) {
            turnOfError()
            return true
        }
        else {
            turnOnError()
            return false
        }
    }

    private fun requiredLength(bin: String): Boolean {
        return bin.length == 6
    }

    private fun digitsOnly(bin: String): Boolean{
        return bin.isDigitsOnly()
    }

    private fun turnOnError(){
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isError = true
        )
    }

    private fun turnOfError(){
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isError = false
        )
    }

}