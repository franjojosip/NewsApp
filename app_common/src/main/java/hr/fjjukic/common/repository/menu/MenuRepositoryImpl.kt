package hr.fjjukic.common.repository.menu

import hr.fjjukic.common.model.network.MenuItem
import hr.fjjukic.common.restinterfaces.ApiRestInterface
import io.reactivex.rxjava3.core.Observable

class MenuRepositoryImpl(
    private val restInterface: ApiRestInterface
) : MenuRepository {
    override fun getMenu(): Observable<List<MenuItem>> {
        return restInterface.getBottomMenuCategories()
    }
}

interface MenuRepository {
    fun getMenu(): Observable<List<MenuItem>>
}