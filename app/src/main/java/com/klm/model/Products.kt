import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products (

	@SerializedName("id") val id : Int,
	@SerializedName("categoryId") val categoryId : Int,
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String,
	@SerializedName("description") val description : String,
	@SerializedName("salePrice") val salePrice : SalePrice
) : Parcelable