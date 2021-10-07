package hr.fjjukic.common.helpers

data class CategoryTabHelper(
    val categoryId: Int,
    val apiRoutePathIndex: Int,
    val tabNameId: Int,
    var tabName: String = ""
)