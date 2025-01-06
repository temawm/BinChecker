package com.example.binchecker.data.api

data class BinResponse(
    val number: NumberDetails?, // Информация о номере карты (длина, поддержка алгоритма Luhn)
    val scheme: String?,        // Схема карты (например, "visa", "mastercard")
    val type: String?,          // Тип карты (например, "debit", "credit")
    val brand: String?,         // Бренд карты (например, "Visa/Dankort")
    val prepaid: Boolean?,      // Является ли карта предоплаченной
    val country: CountryDetails?, // Информация о стране, связанной с картой
    val bank: BankDetails?      // Информация о банке, выпустившем карту
)

data class NumberDetails(
    val length: Int?,           // Длина номера карты (обычно 16)
    val luhn: Boolean?          // Поддерживает ли карта проверку по алгоритму Luhn
)

data class CountryDetails(
    val numeric: String?,       // Числовой код страны (например, "208" для Дании)
    val alpha2: String?,        // Двухбуквенный код страны (например, "DK" для Дании)
    val name: String?,          // Название страны (например, "Denmark")
    val emoji: String?,         // Эмодзи флага страны (например, "🇩🇰")
    val currency: String?,      // Валюта страны (например, "DKK")
    val latitude: String?,         // Географическая широта страны
    val longitude: String?         // Географическая долгота страны
)

data class BankDetails(
    val name: String?,          // Название банка (например, "Jyske Bank")
    val url: String?,           // URL банка (например, "www.jyskebank.dk")
    val phone: String?,         // Телефон банка (например, "+4589893300")
    val city: String?           // Город, где расположен банк (например, "Hjørring")
)
