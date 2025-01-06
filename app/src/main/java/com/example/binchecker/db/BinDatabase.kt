package com.example.binchecker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Database(entities = [BinEntity::class], version = 1)
@Singleton
abstract class AppDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
}
