package com.example.binchecker.domain

import com.example.binchecker.data.api.BinResponse
import com.example.binchecker.db.BinDao
import com.example.binchecker.db.BinEntity
import javax.inject.Inject

class BinRepository @Inject constructor(private val binDao: BinDao) {

    suspend fun saveBinData(binResponse: BinResponse) {
        val binEntity = BinEntity(
            countryName = binResponse.country?.name,
            latitude = binResponse.country?.latitude,
            longitude = binResponse.country?.longitude,
            cardType = binResponse.type,
            bankName = binResponse.bank?.name,
            bankUrl = binResponse.bank?.url,
            bankPhone = binResponse.bank?.phone,
            brandName = binResponse.brand,
            prepaid = binResponse.prepaid.toString(),
            scheme = binResponse.scheme
        )
        binDao.insertBin(binEntity)
    }

    suspend fun getAllBins(): List<BinEntity> {
        return binDao.getAllBins()
    }

    suspend fun deleteBinById(binId: Int) {
        binDao.deleteBinById(binId)
    }
}
