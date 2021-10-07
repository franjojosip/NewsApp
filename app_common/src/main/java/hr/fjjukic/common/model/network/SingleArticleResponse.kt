package hr.fjjukic.common.model.network

import androidx.annotation.Nullable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleArticleResponse(
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
    @SerializedName("featured_image")
    val featured_image: ImageResponse?,
    @Expose
    @SerializedName("published_at")
    val published_at: DateResponse,
    @Expose
    @Nullable
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("uppertitle")
    val uppertitle: String,
    @Expose
    @SerializedName("subtitle")
    val subtitle: String,
    @Expose
    @SerializedName("author")
    val author: String,
    @Expose
    @SerializedName("shares")
    val shares: Int,
    @Expose
    @SerializedName("content")
    val content: List<ContentArticleResponse>,
    @Expose
    @SerializedName("auto_related_articles")
    val auto_related_articles: List<AutoRelatedArticleResponse>,
)

data class ContentArticleResponse(
    @Expose
    @SerializedName("article_id")
    val article_id: Int,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("order")
    val order: Int,
    @Expose
    @SerializedName("data")
    val data: String,
    @Expose
    @SerializedName("image")
    val image: ImageResponse,
    @Expose
    @SerializedName("related")
    val related: List<RelatedResponse>
)

data class RelatedResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("category")
    val category: String,
    @Expose
    @SerializedName("category_color")
    val category_color: String,
    @Expose
    @SerializedName("featured_image_xl")
    val featured_image_l: String,
    @Expose
    @SerializedName("published_at")
    val published_at: String
)

data class AutoRelatedArticleResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("featured_image")
    val featured_image: ImageResponse,
    @Expose
    @Nullable
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("author")
    val author: String,
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
    @SerializedName("published_at")
    val published_at: DateResponse,
    @Expose
    @SerializedName("shares")
    val shares: Int
)
