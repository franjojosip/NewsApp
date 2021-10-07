package ht.fjjukic.home.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import hr.fjjukic.common.contracts.OnNavigateClick
import hr.fjjukic.common.enums.IndexViewHolderType
import hr.fjjukic.common.epoxy.addHeader
import hr.fjjukic.common.epoxy.addIndexArticle
import ht.ferit.fjjukic.app_home.showMore
import ht.fjjukic.home.epoxy.model.slider

class IndexEpoxyController(private val listener: OnNavigateClick): TypedEpoxyController<List<IndexViewHolderType>>() {
    override fun buildModels(data: List<IndexViewHolderType>) {
        data.forEach {
            when(it){
                is IndexViewHolderType.ArticleUI -> addIndexArticle(listener, it)
                is IndexViewHolderType.ShowMoreUI -> addShowMore(listener, it.categoryId)
                is IndexViewHolderType.SliderUI-> addSlider(listener, it)
                is IndexViewHolderType.TitleUI-> addHeader(it.title)
            }
        }
    }
}

fun TypedEpoxyController<*>.addSlider(listener: OnNavigateClick, sliderUI: IndexViewHolderType.SliderUI){
    slider {
        id("slider")
        this.listener(listener)
        this.data(sliderUI.articles)
    }
}

fun TypedEpoxyController<*>.addShowMore(listener: OnNavigateClick, categoryId: Int){
    showMore {
        id(categoryId)
        categoryId(categoryId)
        onNavigateClick(listener)
    }
}