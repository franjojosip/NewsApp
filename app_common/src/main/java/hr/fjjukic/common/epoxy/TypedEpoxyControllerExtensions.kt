package hr.fjjukic.common.epoxy

import androidx.core.text.HtmlCompat
import com.airbnb.epoxy.TypedEpoxyController
import hr.fjjukic.common.*
import hr.fjjukic.common.contracts.OnArticleClick
import hr.fjjukic.common.contracts.OnNavigateClick
import hr.fjjukic.common.enums.ArticleViewHolderType
import hr.fjjukic.common.enums.IndexViewHolderType
import hr.fjjukic.common.model.CategoryArticleUI

fun TypedEpoxyController<*>.addArticleContentImage(url: String){
    image {
        id(url)
        url(url)
    }
}

fun TypedEpoxyController<*>.addArticleContentText(text: String){
    text {
        id(text)
        text(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString())
    }
}

fun TypedEpoxyController<*>.addArticleContentTitle(title: String){
    title {
        id("content-title-$title")
        title(title)
    }
}

fun TypedEpoxyController<*>.addHeader(title: String){
    header {
        id("header-title-$title")
        title(title)
    }
}

fun TypedEpoxyController<*>.addArticleRelated(listener: OnArticleClick, data: ArticleViewHolderType.ArticleRelatedUI){
    article {
        id(data.id)
        onArticleClick(listener)
        data(data)
    }
}

fun TypedEpoxyController<*>.addCategoryArticle(data: CategoryArticleUI, listener: OnArticleClick){
    categoryArticle {
        id(data.id)
        data(data)
        onArticleClick(listener)
    }
}

fun TypedEpoxyController<*>.addIndexArticle(listener: OnNavigateClick, data: IndexViewHolderType.ArticleUI){
    indexArticle {
        id(data.id)
        onNavigateClick(listener)
        data(data)
    }
}