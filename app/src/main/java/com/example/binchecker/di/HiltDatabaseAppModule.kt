package com.example.binchecker.di

import android.content.Context
import androidx.room.Room
import com.example.binchecker.db.AppDatabase
import com.example.binchecker.db.BinDao
import com.example.binchecker.domain.BinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "bin_checker_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBinDao(database: AppDatabase): BinDao {
        return database.binDao()
    }

    @Provides
    @Singleton
    fun provideBinRepository(binDao: BinDao): BinRepository {
        return BinRepository(binDao)
    }
}
