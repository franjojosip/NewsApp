package hr.fjjukic.common.helpers

import hr.fjjukic.common.enums.ApiRoutePath

data class CategoryHelper(
    val categoryId: Int,
    val apiRoutePath: ApiRoutePath,
    val page: Int
)