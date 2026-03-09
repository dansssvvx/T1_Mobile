/* 
AHMAD RAMADHANI R
F1D02310102
*/

// Data Class
data class Produk(
    val id: String,
    val nama: String, 
    val harga: Double, 
    val kategori: String, 
    var stok: Int
)
data class CartItem(
    val produk: Produk, 
    var jumlah: Int
)
data class Customer(
    val id: String, 
    val nama: String, 
    val email: String, 
    val alamat: String?
)

// Sealed Class for OrderStatus
sealed class OrderStatus {
    object Pending : OrderStatus()
    object Processing : OrderStatus()
    object Shipped : OrderStatus()
    object Delivered : OrderStatus()
    object Cancelled : OrderStatus()
    
    override fun toString(): String = this.javaClass.simpleName
}

// Sealed Class for PaymentMethod
sealed class PaymentMethod {
    object Cash : PaymentMethod()
    object Transfer : PaymentMethod()
    object EWallet : PaymentMethod()
    
    override fun toString(): String = this.javaClass.simpleName
}

// 4. Interface Discountable
interface Discountable {
    fun applyDiscount(total: Double): Double
}

class PercentageDiscount(val percent: Double) : Discountable {
    override fun applyDiscount(total: Double): Double = total * (1 - percent / 100)
}

class NominalDiscount(val amount: Double) : Discountable {
    override fun applyDiscount(total: Double): Double = if (total > amount) total - amount else 0.0
}

// 5. Order Data Class
data class Order(
    val id: String,
    val customer: Customer,
    val items: List<CartItem>,
    val status: OrderStatus,
    val paymentMethod: PaymentMethod,
    val totalHarga: Double
)

// Shopping Cart Class
class ShoppingCart {
    private val items = mutableListOf<CartItem>()

    fun tambahProduk(produk: Produk, jumlah: Int) {
        if (produk.stok >= jumlah) {
            val existing = items.find { it.produk.id == produk.id }
            if (existing != null) {
                existing.jumlah += jumlah
            } else {
                items.add(CartItem(produk, jumlah))
            }
            produk.stok -= jumlah
            println("Berhasil menambahkan ${produk.nama} ke keranjang.")
        } else {
            println("Stok ${produk.nama} tidak mencukupi!")
        }
    }

    fun hapusProduk(produkId: String) {
        val item = items.find { it.produk.id == produkId }
        if (item != null) {
            item.produk.stok += item.jumlah
            items.remove(item)
            println("Berhasil menghapus ${item.produk.nama} dari keranjang.")
        }
    }

    // 5. Higher-Order Function for total calculation
    fun hitungTotal(discountCalculator: (Double) -> Double): Double {
        val subtotal = items.sumOf { it.produk.harga * it.jumlah }
        return discountCalculator(subtotal)
    }

    fun getItems(): List<CartItem> = items.toList()
    
    fun clear() = items.clear()
}

// 7. Null Safety and Main System
class TokoOnline {
    val produkList = mutableListOf<Produk>()
    val orders = mutableListOf<Order>()
    val cart = ShoppingCart()

    init {
        // Seed some data
        produkList.add(Produk("P01", "Laptop Gaming", 15000000.0, "Elektronik", 5))
        produkList.add(Produk("P02", "Smartphone", 5000000.0, "Elektronik", 10))
        produkList.add(Produk("P03", "Mouse Wireless", 250000.0, "Aksesoris", 20))
        produkList.add(Produk("P04", "Keyboard Mechanical", 750000.0, "Aksesoris", 15))
    }

    fun tampilkanProduk() {
        println("\n--- DAFTAR PRODUK ---")
        produkList.forEach { println("${it.id}: ${it.nama} - Rp${it.harga} (Stok: ${it.stok})") }
    }

    fun prosesCheckout(customer: Customer, payment: PaymentMethod, discount: Discountable?) {
        val total = cart.hitungTotal { subtotal ->
            discount?.applyDiscount(subtotal) ?: subtotal
        }
        
        if (total > 0) {
            val newOrder = Order(
                id = "ORD-${System.currentTimeMillis()}",
                customer = customer,
                items = cart.getItems(),
                status = OrderStatus.Pending,
                paymentMethod = payment,
                totalHarga = total
            )
            orders.add(newOrder)
            cart.clear()
            println("\nCheckout Berhasil!")
            println("Order ID: ${newOrder.id}")
            println("Total Bayar: Rp$total")
        } else {
            println("Keranjang kosong!")
        }
    }

    fun riwayatPesanan() {
        println("\n--- RIWAYAT PESANAN ---")
        if (orders.isEmpty()) println("Belum ada pesanan.")
        orders.forEach { order ->
            println("Order ${order.id} | Customer: ${order.customer.nama} | Total: Rp${order.totalHarga} | Status: ${order.status}")
        }
    }
}

fun main() {
    val toko = TokoOnline()
    val customer = Customer("C01", "Ahmad Ramadhani", "ahmad@email.com", "Mataram")

    println("===== SELAMAT DATANG DI TOKO ONLINE =====")
    
    toko.tampilkanProduk()

    // Simulasi Belanja
    toko.cart.tambahProduk(toko.produkList[0], 1) // Laptop
    toko.cart.tambahProduk(toko.produkList[2], 2) // 2 Mouse

    // Checkout dengan Diskon 10%
    toko.prosesCheckout(customer, PaymentMethod.EWallet, PercentageDiscount(10.0))

    toko.riwayatPesanan()
    
    // Tampilkan sisa stok
    toko.tampilkanProduk()
}
