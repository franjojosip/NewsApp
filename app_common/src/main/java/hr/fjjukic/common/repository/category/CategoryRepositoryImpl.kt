package hr.fjjukic.common.repository.category

import hr.fjjukic.common.enums.ApiRoutePath
import hr.fjjukic.common.helpers.CategoryHelper
import hr.fjjukic.common.model.network.CategoryResponse
import hr.fjjukic.common.restinterfaces.ApiRestInterface
import io.reactivex.rxjava3.core.Observable

class CategoryRepositoryImpl(
    private val restInterface: ApiRestInterface
) : CategoryRepository {
    override fun getNews(categoryHelper: CategoryHelper): Observable<CategoryResponse> {
        return when (categoryHelper.apiRoutePath) {
            ApiRoutePath.NEWEST -> restInterface.getNewest(
                categoryHelper.categoryId,
                categoryHelper.page
            )
            else -> restInterface.getMostReadable(categoryHelper.categoryId, categoryHelper.page)
        }
    }
}

interface CategoryRepository {
    fun getNews(categoryHelper: CategoryHelper): Observable<CategoryResponse>
}
