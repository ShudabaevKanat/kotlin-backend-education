package ru.education

data class Product(
    val id: Long,
    val name: String,
    val priceKopecks: Int,
    val quantity: Int,
    val isAvailable: Boolean,
    val hasDiscount: Boolean,
    val hasPromoCode: Boolean,
    val status: ProductStatus,
    val type: ProductType,
    val description: String?
)
