package hr.fjjukic.common.enums

import hr.fjjukic.common.model.ArticleInfoUI
import hr.fjjukic.common.model.CategoryUI

sealed class ArticleViewHolderType {
    data class ArticleUI(
        val articleId: Int,
        val imageUrl: String,
        val title: String?,
        val category: CategoryUI
    ) : ArticleViewHolderType() {
        companion object
    }

    data class ArticleDescriptionUI(
        val title: String?,
        val subtitle: String,
        val articleInfoUI: ArticleInfoUI
    ) : ArticleViewHolderType()

    data class ArticleContentTitleUI(
        val title: String
    ) : ArticleViewHolderType()

    data class ArticleContentTextUI(
        val text: String
    ) : ArticleViewHolderType()

    data class ArticleContentImageUI(
        val image_url: String
    ) : ArticleViewHolderType()

    data class ArticleRelatedUI(
        val id: String,
        val articleUI: hr.fjjukic.common.model.ArticleUI,
        val articleInfoUI: ArticleInfoUI
    ) : ArticleViewHolderType()

}