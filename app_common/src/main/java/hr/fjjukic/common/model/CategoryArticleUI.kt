package hr.fjjukic.common.model

data class CategoryArticleUI(
    val id: String,
    val articleId: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val published: String,
    val num_of_shares: String,
    val category: CategoryUI
)
