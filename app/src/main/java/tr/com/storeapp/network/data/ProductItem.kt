package tr.com.storeapp.network.data


import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("date")
    val date: String?,
    @SerializedName("marketName")
    val marketName: String?,
    @SerializedName("month")
    val month: String?,
    @SerializedName("orderName")
    val orderName: String?,
    @SerializedName("productDetail")
    val productDetail: ProductDetail?,
    @SerializedName("productPrice")
    val productPrice: Double?,
    @SerializedName("productState")
    val productState: String?
)