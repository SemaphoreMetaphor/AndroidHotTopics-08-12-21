package com.appnio.androidhottopics.data


data class CreditCard(
    val name: String,
    val amount: Int,
    val firstDigits: String,
    val lastDigits: String
)

data class Transaction(val name: String, val payee: String, val amount: Int)

val creditCards = listOf(
    CreditCard("Brandon Baker", 12, "3145", "9856"),
    CreditCard("Brandon Baker", 9800, "6548", "2589"),
    CreditCard("Brandon Baker", 450, "7895", "2545"),
    CreditCard("Brandon Baker", 800000, "9878", "3256"),
    CreditCard("Brandon Baker", 12000, "4567", "9876")
)

val transactions = listOf(
    Transaction("Starbucks Denton", "Starbucks Lmt.", 12),
    Transaction("Starbucks Chicago", "Starbucks Lmt.", 8),
    Transaction("Starbucks Dallas", "Starbucks Lmt.", 5),
    Transaction("Starbucks Highland Village", "Starbucks Lmt.", 12),
    Transaction("Starbucks Nashville", "Starbucks Lmt.", 79),
    Transaction("Starbucks Albuquerque", "Starbucks Lmt.", 16),
    Transaction("Starbucks El Paso", "Starbucks Lmt.", 14),
    Transaction("Starbucks Orlando", "Starbucks Lmt.", 155),
    Transaction("Starbucks Disney World", "Starbucks Lmt.", 15),
    Transaction("Starbucks in the Kroger", "Starbucks Lmt.", 22),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 52),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 45),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 12),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 11),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 9),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 7),
    Transaction("Starbucks Denton", "Starbucks Lmt.", 73)
)

