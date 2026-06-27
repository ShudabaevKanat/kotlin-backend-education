package ru.education

fun calculateRubles(totalPriceKopecks: Int): Int = totalPriceKopecks / 100

fun calculateKopecksRest(totalPriceKopecks: Int): Int = totalPriceKopecks % 100

fun printProductInfo(product: Product, totalPriceKopecks: Int) {
    println("Product: ${product.name}")
    println("Product id: ${product.id}")
    println("Price: ${product.priceKopecks} kopecks")
    println("Quantity: ${product.quantity}")
    println("Available: ${product.isAvailable}")
    println("Total: $totalPriceKopecks kopecks")
}

fun printTotalPrice(totalRubles: Int, totalKopecksRest: Int) {
    println("Total: $totalRubles rubles $totalKopecksRest kopecks")
}

fun main() {


    val product1 = Product(
        id = 1243124L,
        name = "Шоколад",
        priceKopecks = 9999,
        quantity = 5,
        isAvailable = true,
        hasDiscount = false,
        hasPromoCode = false,
        status = ProductStatus.NEW,
        type = ProductType.FOOD,
        description = null,
    )

    val product2 = Product(
        id = 1243128L,
        name = "Джинсы",
        priceKopecks = 199999,
        quantity = 8,
        isAvailable = true,
        hasDiscount = false,
        hasPromoCode = false,
        status = ProductStatus.AVAILABLE,
        type = ProductType.CLOTHES,
        description = null,
    )

    val product3 = Product(
        id = 12431214L,
        name = "Телефон",
        priceKopecks = 2799000,
        quantity = 23,
        isAvailable = true,
        hasDiscount = false,
        hasPromoCode = true,
        status = ProductStatus.AVAILABLE,
        type = ProductType.ELECTRONICS,
        description = "Мощный сочный",
    )

    val updatedProduct = product1.copy(id = 1243125L, quantity = 10)
    println("Original quantity: ${product1.quantity}")
    println("Updated quantity: ${updatedProduct.quantity}")

    val publishedProduct = product1.copy(id = 1243126L, status = ProductStatus.AVAILABLE)
    println("Original status: ${product1.status}")
    println("Published status: ${publishedProduct.status}")

    val invalidProduct = product1.copy(id = 1243127L, quantity = 0)

    val products = listOf(product1, product2, product3, invalidProduct)

    println("Products count: ${products.size}")
    println("First product name: ${products[0].name}")
    println("Second product quantity: ${products[1].quantity}")
    println("Third product status: ${products[2].status}")

    val productService = ProductService()

    for (productItem in products) {
        try {
            productService.validateProductData(productItem)

            val itemTotalPriceKopecks = productService.calculateTotalPriceKopecks(productItem)
            val itemIsExpensive = productService.isExpensive(productItem)
            val itemStatusDescription = productService.getProductStatusDescription(productItem)
            val itemTypeDescription = productService.getProductTypeDescription(productItem)

            println("Product in list: ${productItem.name}, quantity: ${productItem.quantity}, status: ${productItem.status}, total: $itemTotalPriceKopecks, expensive: $itemIsExpensive, status description: $itemStatusDescription, type description: $itemTypeDescription")
        } catch (e: IllegalArgumentException) {
            println("Invalid product: ${productItem.name}, error: ${e.message}")
        }
    }

    val availableProducts = productService.getAvailableProducts(products)

    println("Available products: ${availableProducts.size}")

    for (availableProduct in availableProducts) {
        println("Available product: ${availableProduct.name}, status: ${availableProduct.status}")
    }

    val firstAvailableProduct = productService.findFirstAvailableProduct(products)
    println("First available product: ${firstAvailableProduct?.name ?: "Not found"}")

    val firstIdProduct = productService.findProductById(products, 1243124L)
    println("First id: ${firstIdProduct?.id ?: "Not found"}")

    val requiredProduct = productService.requireProductById(products, 1243124L)
    println("Required product: ${requiredProduct.name}")

    val productsWithUpdateQuantity = productService.updateProductQuantity(products, 1243124L, newQuantity = 200)
    val productWithUpdateQuantity = productService.requireProductById(productsWithUpdateQuantity, 1243124L)
    println("Updated product quantity: ${productWithUpdateQuantity.quantity}")

    val productsWithUpdateStatus = productService.updateProductStatus(productsWithUpdateQuantity, 1243124L, ProductStatus.BLOCKED)
    val productWithUpdateStatus = productService.requireProductById(productsWithUpdateStatus, 1243124L)
    println("Updated product status: ${productWithUpdateStatus.status}")

    val productsAfterDelete = productService.deleteProductById(products, 1243124L)
    println("Old products size: ${products.size}")
    println("Products after delete size: ${productsAfterDelete.size}")

    val deleteProduct = productService.findProductById(productsAfterDelete, 1243124L)
    println("Deleted product: ${deleteProduct?.name ?: "Not found"}")

    try {
        val missingProduct = productService.requireProductById(products, 124312124L)
        println("Missing product: ${missingProduct.name}")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }

    val productNames = productService.getProductNames(products)
    println("Product names: $productNames")

    val productIds = productService.getProductIds(products)
    println("Product ids: $productIds")

    val productTotalPrices = productService.getProductTotalPrices(products)
    println("Product total prices: $productTotalPrices")

    val allProductPrices = productService.calculateProductsTotalPrice(products)
    println("All products total price: $allProductPrices")

    val allProductPricesSumOf = products.sumOf { productItem -> productService.calculateTotalPriceKopecks(productItem) }
    println("All products total price sumOf: $allProductPricesSumOf")

    val availableProductsTotalPrice = productService.calculateAvailableProductsTotalPrice(products)
    println("Available products total price: $availableProductsTotalPrice")

    val mutableProducts = mutableListOf(product1, updatedProduct)

    println("Mutable products count before add: ${mutableProducts.size}")

    mutableProducts.add(publishedProduct)

    println("Mutable products count after add: ${mutableProducts.size}")

    mutableProducts.remove(product1)

    println("Mutable products count after remove: ${mutableProducts.size}")

    for (mutableProduct in mutableProducts) {
        println("Mutable product: ${mutableProduct.name}, status: ${mutableProduct.status}")
    }

    mutableProducts[0] = mutableProducts[0].copy(quantity = 20)
    println("First mutable product updated quantity: ${mutableProducts[0].quantity}")

    val productStatuses = productService.getProductStatuses(products)
    println("Product statuses: $productStatuses")

    val productTypes = productService.getProductTypes(products)
    println("Product types: $productTypes")

    val uniqueProductIds = productService.getUniqueProductIds(products)
    println("Unique product ids: $uniqueProductIds")

    val productsById = productService.getProductsById(products)
    println("Products by id: $productsById")

    val productFromMap = productsById[1243124L]
    println("Product from map: ${productFromMap?.name ?: "Not found"}")
    println("Product ids from map: ${productsById.keys}")
    println("Products from map: ${productsById.values}")

    val hasProductWithId = productsById.containsKey(1243124L)
    println("Has product with id 1243124: $hasProductWithId")

    try {
        productService.validateProductData(product1)

        val totalPriceKopecks = productService.calculateTotalPriceKopecks(product1)
        val totalRubles = calculateRubles(totalPriceKopecks)
        val totalKopecksRest = calculateKopecksRest(totalPriceKopecks)
        val productHasEnoughQuantity = productService.hasEnoughQuantity(product1)
        val productIsExpensive = productService.isExpensive(product1)

        printProductInfo(product1, totalPriceKopecks)

        printTotalPrice(totalRubles, totalKopecksRest)

        println("Has enough quantity: $productHasEnoughQuantity")
        println("Is expensive: $productIsExpensive")

        if (product1.isAvailable) {
            println("Product is available")
        } else {
            println("Product is not available")
        }

        if (productHasEnoughQuantity) {
            println("Product has enough quantity")
        } else {
            println("Product is out of stock")
        }

        if (productIsExpensive) {
            println("Product is expensive")
        } else {
            println("Product price is normal")
        }

        if (product1.isAvailable && productHasEnoughQuantity) {
            println("Product can be bought")
        } else {
            println("Product cannot be bought")
        }

        if (product1.hasDiscount || product1.hasPromoCode) {
            println("User has some benefit")
        } else {
            println("User has no benefits")
        }

        if (!product1.isAvailable) {
            println("Product is hidden because it is not available")
        } else {
            println("Product can be shown to user")
        }

        if (!product1.hasDiscount && !product1.hasPromoCode) {
            println("Product has no discount and no promo code")
        } else {
            println("Product has at least one benefit")
        }

        val productTypeDescription = productService.getProductTypeDescription(product1)

        println("Description: $productTypeDescription")

        val productStatusDescription = productService.getProductStatusDescription(product1)

        println("Status description: $productStatusDescription")

        val productDescriptionLength = productService.getProductDescriptionLength(product1)
        println("Description length: $productDescriptionLength")

        val productDescriptionText = productService.getProductDescriptionText(product1)
        println("Product description: $productDescriptionText")

    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}
