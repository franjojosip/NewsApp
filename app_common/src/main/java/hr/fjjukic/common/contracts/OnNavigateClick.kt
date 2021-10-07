package hr.fjjukic.common.contracts

interface OnNavigateClick {
    fun navigateToCategory(categoryId: Int)
    fun navigateToArticle(articleId: Int)
}