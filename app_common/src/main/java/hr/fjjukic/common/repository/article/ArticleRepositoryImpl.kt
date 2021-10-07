package hr.fjjukic.common.repository.article

import hr.fjjukic.common.model.network.SingleArticleResponse
import hr.fjjukic.common.restinterfaces.ApiRestInterface
import io.reactivex.rxjava3.core.Observable

class ArticleRepositoryImpl(
    private val restInterface: ApiRestInterface
) : ArticleRepository {
    override fun getArticle(articleId: Int, page: Int): Observable<SingleArticleResponse> {
        return restInterface.getSingleArticle(articleId, page)
    }
}

interface ArticleRepository {
    fun getArticle(articleId: Int, page: Int): Observable<SingleArticleResponse>
}