package hr.fjjukic.common.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("color")
    val color: String,
    @Expose
    @SerializedName("position_no")
    val position_no: Int,
    @Expose
    @SerializedName("category_id")
    val category_id: Int,
    @Expose
    @SerializedName("articles")
    val articles: List<ArticleResponse>
)

data class ArticleResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("category_id")
    val category_id: Int,
    @Expose
    @SerializedName("category")
    val category: String,
    @Expose
    @SerializedName("category_color")
    val category_color: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("subtitle")
    val subtitle: String,
    @Expose
    @SerializedName("intro")
    val intro: String,
    @Expose
    @SerializedName("intro_short")
    val intro_short: String,
    @Expose
    @SerializedName("intro_shorter")
    val intro_shorter: String,
    @Expose
    @SerializedName("hits")
    val hits: Int,
    @Expose
    @SerializedName("featured_image")
    val featured_image: ImageResponse,

)
data class ImageResponse(
    @Expose
    @SerializedName("original")
    val original: String,
    @Expose
    @SerializedName("slider")
    val slider: String
)

data class DateResponse(
    @Expose
    @SerializedName("date")
    val date: String,
    @Expose
    @SerializedName("timezone_type")
    val timezone_type: Int,
    @Expose
    @SerializedName("timezone")
    val timezone: String
)


