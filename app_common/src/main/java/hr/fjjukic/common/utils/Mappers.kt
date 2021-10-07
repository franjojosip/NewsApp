package hr.fjjukic.common.utils

import android.annotation.SuppressLint
import hr.fjjukic.common.R
import hr.fjjukic.common.enums.ArticleViewHolderType
import hr.fjjukic.common.enums.FragmentType
import hr.fjjukic.common.enums.IndexViewHolderType
import hr.fjjukic.common.model.*
import hr.fjjukic.common.model.network.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun mapMenuResponseToMenuUI(menu: List<MenuItem>): MutableList<MenuUI> {
    return menu.mapIndexed { index, item ->
        MenuUI(
            title = item.title,
            fragmentType = getFragmentType(index, menu.size),
            categoryId = item.category_id
        )
    }.toMutableList()
}

fun getFragmentType(index: Int, size: Int): FragmentType {
    return when (index) {
        0 -> FragmentType.INDEX
        size - 1 -> FragmentType.FAVORITE
        else -> FragmentType.CATEGORY
    }
}


fun mapNewsResponseToNewsList(newsResponses: List<NewsResponse>): MutableList<IndexViewHolderType> {
    val newsList: MutableList<IndexViewHolderType> = mutableListOf()

    newsResponses.forEachIndexed { index, news ->
        if (index == 0) {
            newsList.add(mapArticleResponseToSliderUI(news.articles))
        } else {
            newsList.add(IndexViewHolderType.TitleUI(title = news.title, underline_color = news.color))
            news.articles.toList().take(8).forEach { article ->
                newsList.add(
                    IndexViewHolderType.ArticleUI(
                        id = "article-${article.id}",
                        articleId = article.id,
                        imageUrl = "https://avaz.ba" + article.featured_image.original,
                        title = article.title,
                        category = CategoryUI(
                            id = article.category_id,
                            name = article.category,
                            color = news.color,
                        )
                    )
                )
            }
            newsList.add(IndexViewHolderType.ShowMoreUI(titleId = R.string.show_more, categoryId = news.category_id))
        }
    }
    return newsList
}

fun mapArticleResponseToSliderUI(news: List<ArticleResponse>): IndexViewHolderType.SliderUI {
    return IndexViewHolderType.SliderUI(news.map { article ->
        SlideUI(
            articleId = article.id,
            imageUrl = "https://avaz.ba" + article.featured_image.slider,
            title = article.title,
            category = CategoryUI(
                id = article.category_id,
                name = article.category,
                color = article.category_color,
            )
        )
    }
    )
}

fun mapNewsResponseToCategoryArticleUI(news: List<CategoryArticleResponse>): MutableList<CategoryArticleUI> {
    return news.map { article ->
        CategoryArticleUI(
            id = "category-article-${article.id}",
            articleId = article.id,
            imageUrl = "https://avaz.ba" + article.featured_image.original,
            title = article.title,
            description = article.intro_shorter,
            category = CategoryUI(
                id = article.category_id,
                name = article.category,
                color = article.category_color,
            ),
            published = article.published_at_humans,
            num_of_shares = article.shares.toString()
        )
    }.toMutableList()
}

fun replacePublished(published: String): String {
    return when {
        published.contains("minuta") -> {
            published.replace("minuta", "min.")
        }
        published.contains("minut") -> {
            published.replace("minut", "min.")
        }
        else -> published
    }
}


@SuppressLint("SimpleDateFormat")
fun mapSingleArticleResponseToArticleList(response: SingleArticleResponse): MutableList<ArticleViewHolderType> {
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")
    val parserRelated = SimpleDateFormat("yyyy.MM.dd HH:mm")
    val articleList: MutableList<ArticleViewHolderType> = mutableListOf()

    if(response.featured_image != null){
        articleList.add(
            ArticleViewHolderType.ArticleUI(
                articleId = response.id,
                imageUrl = "https://avaz.ba" + response.featured_image.original,
                title = response.uppertitle,
                CategoryUI(
                    id = response.category_id,
                    name = response.category,
                    color = response.category_color
                )
            )
        )
    }



    articleList.add(
        ArticleViewHolderType.ArticleDescriptionUI(
            title = response.title,
            subtitle = response.subtitle,
            articleInfoUI = ArticleInfoUI(
                author = response.author,
                num_of_shares = response.shares.toString(),
                published = getDateMessage(parser.parse(response.published_at.date))
            )
        )
    )

    if (!response.content.isNullOrEmpty()) {
        response.content.sortedBy { item -> item.order }
        response.content.forEach { contentItem ->
            if (contentItem.type.toLowerCase(Locale.getDefault()) == "related") {
                articleList.add(ArticleViewHolderType.ArticleContentTitleUI("Povezano"))
            }
            when (contentItem.type) {
                "text" -> articleList.add(ArticleViewHolderType.ArticleContentTextUI(contentItem.data))
                "image" -> articleList.add(ArticleViewHolderType.ArticleContentImageUI("https://avaz.ba" + contentItem.image.original))
                "related" -> {
                    contentItem.related.forEach { related ->
                        articleList.add(
                            ArticleViewHolderType.ArticleRelatedUI(
                                id = "article-related-${related.id}",
                                ArticleUI(
                                    articleId = related.id,
                                    imageUrl = "https://avaz.ba" + related.featured_image_l,
                                    title = related.title,
                                    CategoryUI(
                                        id = 1,
                                        name = related.category,
                                        color = related.category_color
                                    )
                                ), ArticleInfoUI(
                                    author = "",
                                    num_of_shares = "0",
                                    published = getDateMessage(parserRelated.parse(related.published_at))
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    if (!response.auto_related_articles.isNullOrEmpty()) {
        articleList.add(ArticleViewHolderType.ArticleContentTitleUI("Izdvojeno"))
        response.auto_related_articles.forEach { relatedItem ->
            articleList.add(
                ArticleViewHolderType.ArticleRelatedUI(
                    id = "article-auto-related-${relatedItem.id}",
                    ArticleUI(
                        articleId = relatedItem.id,
                        imageUrl = "https://avaz.ba" + relatedItem.featured_image.original,
                        title = relatedItem.title,
                        CategoryUI(
                            id = relatedItem.category_id,
                            name = relatedItem.category,
                            color = relatedItem.category_color
                        )
                    ), ArticleInfoUI(
                        author = relatedItem.author,
                        num_of_shares = relatedItem.shares.toString(),
                        published = getDateMessage(parser.parse(relatedItem.published_at.date))
                    )
                )
            )
        }
    }

    return articleList
}

fun getDateMessage(date: Date?): String {
    var message = ""
    date?.let { oldDate ->
        val currentDate = Date()
        val timeDifference = currentDate.time - oldDate.time
        when {
            TimeUnit.MILLISECONDS.toMinutes(timeDifference) < 5 -> {
                message = "upravo sada"
            }
            TimeUnit.MILLISECONDS.toMinutes(timeDifference) < 60 -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference)
                message = "prije $minutes min."
            }
            TimeUnit.MILLISECONDS.toHours(timeDifference) < 24 -> {
                val hours = TimeUnit.MILLISECONDS.toHours(timeDifference)
                message = "prije $hours sati"
            }
            else -> {
                val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
                message = "prije $days dana"
            }
        }
    }
    return message
}
