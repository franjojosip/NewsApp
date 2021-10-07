package hr.fjjukic.common.enums

import hr.fjjukic.common.model.CategoryUI
import hr.fjjukic.common.model.SlideUI

sealed class IndexViewHolderType {

    data class SliderUI(
        val articles: List<SlideUI>
    ) : IndexViewHolderType()

    data class ShowMoreUI(
        val categoryId: Int,
        val titleId: Int
    ) : IndexViewHolderType()

    data class ArticleUI(
        val id: String,
        val articleId: Int,
        val imageUrl: String,
        val title: String?,
        val category: CategoryUI
    ) : IndexViewHolderType()

    data class TitleUI(
        val title: String,
        val underline_color: String? = ""
    ) : IndexViewHolderType()

}
