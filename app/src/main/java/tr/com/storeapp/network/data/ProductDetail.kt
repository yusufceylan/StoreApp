package tr.com.storeapp.network.data


import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("orderDetail")
    val orderDetail: String?,
    @SerializedName("summaryPrice")
    val summaryPrice: Double?
)