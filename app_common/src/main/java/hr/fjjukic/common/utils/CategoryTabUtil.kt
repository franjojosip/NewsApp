package hr.fjjukic.common.utils

import hr.fjjukic.common.R
import hr.fjjukic.common.enums.ApiRoutePath
import hr.fjjukic.common.helpers.CategoryTabHelper

class CategoryTabUtil {
    companion object {
        fun getCategoryTabList(categoryId: Int): List<CategoryTabHelper> {
            return listOf(
                CategoryTabHelper(categoryId, ApiRoutePath.NEWEST.ordinal, R.string.tab_newest),
                CategoryTabHelper(categoryId, ApiRoutePath.MOST_READ.ordinal, R.string.tab_most_read)
            )
        }
    }
}
