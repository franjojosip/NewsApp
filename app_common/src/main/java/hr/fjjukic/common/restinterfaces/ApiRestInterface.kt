package hr.fjjukic.common.restinterfaces

import hr.fjjukic.common.helpers.Constants
import hr.fjjukic.common.model.network.CategoryResponse
import hr.fjjukic.common.model.network.MenuItem
import hr.fjjukic.common.model.network.NewsResponse
import hr.fjjukic.common.model.network.SingleArticleResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRestInterface {
    @GET("index")
    fun getIndex(
        @Query("api_token") api_token: String = Constants.API_TOKEN
    ): Observable<List<NewsResponse>>

    @GET("clanak/{id}")
    fun getSingleArticle(
        @Path("id") id: Int,
        @Query("stranica") page: Int,
        @Query("api_token") api_token: String = Constants.API_TOKEN
    ): Observable<SingleArticleResponse>

    @GET("najnovije/{id}")
    fun getNewest(
        @Path("id") id: Int,
        @Query("stranica") page: Int,
        @Query("api_token") api_token: String = Constants.API_TOKEN
    ): Observable<CategoryResponse>

    @GET("najcitanije/{id}")
    fun getMostReadable(
        @Path("id") id: Int,
        @Query("stranica") page: Int,
        @Query("api_token") api_token: String = Constants.API_TOKEN
    ): Observable<CategoryResponse>

    @GET("menu-bottom")
    fun getBottomMenuCategories(
        @Query("api_token") api_token: String = Constants.API_TOKEN
    ): Observable<List<MenuItem>>
}