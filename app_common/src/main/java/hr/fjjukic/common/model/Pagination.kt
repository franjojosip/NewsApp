package hr.fjjukic.common.model

data class Pagination(
    var current_page: Int = 1,
    var isLastPage: Boolean = false
){
    fun resetPage() {
        current_page = 1
        isLastPage = false
    }
}
