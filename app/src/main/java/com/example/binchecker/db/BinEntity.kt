package com.example.binchecker.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_response")
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val countryName: String?,         // Название страны
    val latitude: String?,            // Географическая широта
    val longitude: String?,           // Географическая долгота
    val cardType: String?,            // Тип карты (например, Visa)
    val bankName: String?,            // Название банка
    val bankUrl: String?,             // URL банка
    val bankPhone: String?,           // Телефон банка
    val brandName: String?,           // Бренд
    val prepaid: String?,             // Предоплата
    val scheme: String?,              // Схема
)
