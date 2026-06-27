package ru.education

class ProductService {
    fun calculateTotalPriceKopecks(product: Product): Int = product.quantity * product.priceKopecks

    fun hasEnoughQuantity(product: Product): Boolean = product.quantity > 0

    fun isExpensive(product: Product): Boolean = calculateTotalPriceKopecks(product) > 100000

    fun validateProductData(product: Product) {
        if (product.priceKopecks <= 0) {
            throw IllegalArgumentException("Price must be greater than zero")
        }
        if (product.quantity <= 0) {
            throw IllegalArgumentException("Quantity must be greater than zero")
        }
    }

    fun getProductStatusDescription(product: Product): String = when (product.status) {
        ProductStatus.AVAILABLE -> "Product is ready for sale"
        ProductStatus.NEW -> "Product is not published yet"
        ProductStatus.OUT_OF_STOCK -> "Product is out of stock"
        ProductStatus.BLOCKED -> "Product is blocked"
    }

    fun getProductTypeDescription(product: Product): String = when (product.type) {
        ProductType.FOOD -> "Product type is food"
        ProductType.ELECTRONICS -> "Product type is electronics"
        ProductType.CLOTHES -> "Product type is clothes"
    }

    fun getProductDescriptionLength(product: Product): Int? = product.description?.length

    fun getProductDescriptionText(product: Product): String = product.description ?: "No description"

    fun getAvailableProducts(products: List<Product>): List<Product> =
        products.filter { productItem -> productItem.status == ProductStatus.AVAILABLE }

    fun getProductNames(products: List<Product>): List<String> =
        products.map { productItem -> productItem.name }

    fun getProductIds(products: List<Product>): List<Long> =
        products.map { productItem -> productItem.id }

    fun getProductTotalPrices(products: List<Product>): List<Int> =
        products.map { productItem -> calculateTotalPriceKopecks(productItem) }

    fun calculateProductsTotalPrice(products: List<Product>): Int =
        products.sumOf { productItem -> calculateTotalPriceKopecks(productItem) }

    fun calculateAvailableProductsTotalPrice(products: List<Product>): Int {
        val availableProducts = getAvailableProducts(products)
        return calculateProductsTotalPrice(availableProducts)
    }

    fun getProductStatuses(products: List<Product>): Set<ProductStatus> =
        products.map { productItem -> productItem.status }.toSet()

    fun getUniqueProductIds(products: List<Product>): Set<Long> =
        products.map { productItem -> productItem.id }.toSet()

    fun getProductsById(products: List<Product>): Map<Long, Product> =
        products.associateBy { productItem -> productItem.id }

    fun getProductTypes(products: List<Product>): Set<ProductType> =
        products.map { productItem -> productItem.type }.toSet()

    fun findProductById(products: List<Product>, productId: Long): Product? =
        products.find { productItem -> productItem.id == productId }

    fun findFirstAvailableProduct(products: List<Product>): Product? =
        products.find { productItem -> productItem.status == ProductStatus.AVAILABLE }

    fun requireProductById(products: List<Product>, productId: Long): Product {
        val product = findProductById(products, productId)
        if (product == null) {
            throw IllegalArgumentException("Product with id $productId not found")
        }
        return product
    }
}
