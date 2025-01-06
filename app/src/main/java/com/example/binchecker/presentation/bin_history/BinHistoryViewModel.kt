package com.example.binchecker.presentation.bin_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.data.api.BinListApi
import com.example.binchecker.db.BinEntity
import com.example.binchecker.domain.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        getAllBins()
    }

    private fun getAllBins() {
        viewModelScope.launch {
            val bins = binRepository.getAllBins()
            _binsList.value = bins
        }
    }
}