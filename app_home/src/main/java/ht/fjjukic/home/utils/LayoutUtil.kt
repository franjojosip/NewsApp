package ht.fjjukic.home.utils

import hr.fjjukic.common.enums.ViewHolderType
import ht.ferit.fjjukic.app_home.R

fun getIndexLayout(viewType: Int): Int {
    return when (viewType) {
        ViewHolderType.SLIDER.ordinal -> R.layout.cell_slider
        ViewHolderType.HEADER.ordinal -> R.layout.cell_header
        ViewHolderType.ARTICLE.ordinal -> R.layout.cell_index_article
        else -> R.layout.cell_show_more
    }
}