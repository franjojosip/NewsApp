package hr.fjjukic.common.repository.news

import hr.fjjukic.common.model.network.NewsResponse
import hr.fjjukic.common.restinterfaces.ApiRestInterface
import io.reactivex.rxjava3.core.Observable

class NewsRepositoryImpl(
    private val restInterface: ApiRestInterface
) : NewsRepository {
    override fun getIndexNews(): Observable<List<NewsResponse>> {
        return restInterface.getIndex()
    }
}

interface NewsRepository {
    fun getIndexNews(): Observable<List<NewsResponse>>
}