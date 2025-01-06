package com.example.binchecker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(bin: BinEntity)

    @Query("SELECT * FROM bin_response")
    suspend fun getAllBins(): List<BinEntity>
}
