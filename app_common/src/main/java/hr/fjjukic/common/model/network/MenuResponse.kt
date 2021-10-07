package hr.fjjukic.common.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MenuItem(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("color")
    val color: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("priority")
    val priority: Int,
    @Expose
    @SerializedName("category_id")
    val category_id: Int,
    @Expose
    @SerializedName("category_color")
    val category_color: String?,
    @Expose
    @SerializedName("parent_id")
    val parent_id: Int,
    @Expose
    @SerializedName("main_category_id")
    val main_category_id: Int
)
