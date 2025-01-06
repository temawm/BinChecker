package com.example.binchecker.presentation.bin_find_info

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.data.api.BinListApi
import com.example.binchecker.data.api.BinResponse
import com.example.binchecker.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.checkerframework.checker.units.qual.s
import javax.inject.Inject

data class UiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val binField: String = "",
)



@HiltViewModel
class BinFindInfoViewModel @Inject constructor(
    private val api: BinListApi,
    private val binRepository: BinRepository
) : ViewModel() {



    private val _binState = MutableStateFlow<BinResponse?>(null)
    val binState: StateFlow<BinResponse?> = _binState

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private suspend fun saveBinData(binResponse: BinResponse) {
        viewModelScope.launch {
            binRepository.saveBinData(binResponse)
        }
    }

    fun updateState(bin: String) {
        _uiState.value = _uiState.value.copy(
            binField = bin
        )
    }

    fun sendBin(bin: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                if (fetchData(bin)) {
                    _binState.value?.let {
                        saveBinData(it)
                    }
                }
            }
        }

    }

    private suspend fun fetchData(bin: String): Boolean {
        if (checkRequirements(bin)) {
            val fetchResult: BinResponse = api.getBinDetails(bin = bin)
            _binState.value = fetchResult
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
        return (bin.length in 6..8)
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