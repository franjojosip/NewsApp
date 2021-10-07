package hr.fjjukic.common.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("category_id")
    val category_id: Int,
    @Expose
    @SerializedName("articles")
    val articles: List<CategoryArticleResponse>
)

data class CategoryArticleResponse(
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
    @SerializedName("intro_shorter")
    val intro_shorter: String,
    @Expose
    @SerializedName("shares")
    val shares: Int,
    @Expose
    @SerializedName("published_at_humans")
    val published_at_humans: String,
    @Expose
    @SerializedName("featured_image")
    val featured_image: ImageResponse,
)
