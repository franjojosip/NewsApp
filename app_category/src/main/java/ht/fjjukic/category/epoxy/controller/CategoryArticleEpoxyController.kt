package ht.fjjukic.category.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import hr.fjjukic.common.contracts.OnArticleClick
import hr.fjjukic.common.epoxy.addCategoryArticle
import hr.fjjukic.common.model.CategoryArticleUI

class CategoryArticleEpoxyController(private val listener: OnArticleClick): TypedEpoxyController<List<CategoryArticleUI>>() {
    override fun buildModels(data: List<CategoryArticleUI>) {
        data.forEach {
            addCategoryArticle(it, listener)
        }
    }
}