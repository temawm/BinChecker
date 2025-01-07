package com.example.binchecker.presentation.bin_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.db.BinEntity
import com.example.binchecker.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinHistoryViewModel @Inject constructor(
    private val binRepository: BinRepository
) : ViewModel() {

    private val _binsList = MutableStateFlow<List<BinEntity>>(emptyList())
    val binsList: StateFlow<List<BinEntity>> = _binsList

    fun getAllBins() {
        viewModelScope.launch {
            val bins = binRepository.getAllBins()
            _binsList.value = bins
        }
    }

    fun deleteBinById(binId: Int){
        viewModelScope.launch {
            binRepository.deleteBinById(binId)
            val updatedBins = binRepository.getAllBins()
            delay(500)
            _binsList.value = updatedBins
        }
    }


}