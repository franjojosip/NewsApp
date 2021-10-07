package ht.ferit.fjjukic.article.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import hr.fjjukic.common.contracts.OnArticleClick
import hr.fjjukic.common.enums.ArticleViewHolderType
import hr.fjjukic.common.epoxy.addArticleContentImage
import hr.fjjukic.common.epoxy.addArticleContentText
import hr.fjjukic.common.epoxy.addArticleContentTitle
import hr.fjjukic.common.epoxy.addArticleRelated
import ht.ferit.fjjukic.app_article.articleHeader
import ht.ferit.fjjukic.app_article.description

class ArticleEpoxyController(private val listener: OnArticleClick): TypedEpoxyController<MutableList<ArticleViewHolderType>>() {
    override fun buildModels(data: MutableList<ArticleViewHolderType>) {
        data.forEach{
            when(it){
                is ArticleViewHolderType.ArticleRelatedUI -> addArticleRelated(listener, it)
                is ArticleViewHolderType.ArticleContentImageUI -> addArticleContentImage(it.image_url)
                is ArticleViewHolderType.ArticleContentTextUI -> addArticleContentText(it.text)
                is ArticleViewHolderType.ArticleContentTitleUI -> addArticleContentTitle(it.title)
                is ArticleViewHolderType.ArticleDescriptionUI -> addArticleDescription(it)
                is ArticleViewHolderType.ArticleUI -> addArticleHeader(it)
            }
        }
    }
}

fun TypedEpoxyController<*>.addArticleDescription(data: ArticleViewHolderType.ArticleDescriptionUI){
    description {
        id(data.title)
        data(data)
    }
}

fun TypedEpoxyController<*>.addArticleHeader(data: ArticleViewHolderType.ArticleUI){
    articleHeader {
        id("header${data.articleId}")
        data(data)
    }
}