package ht.fjjukic.category.viewmodel

import androidx.lifecycle.ViewModel
import hr.fjjukic.common.enums.ActionType
import hr.fjjukic.common.enums.ApiRoutePath
import hr.fjjukic.common.events.LoaderUI
import hr.fjjukic.common.events.SnackBarUI
import hr.fjjukic.common.helpers.CategoryHelper
import hr.fjjukic.common.model.CategoryArticleUI
import hr.fjjukic.common.model.Pagination
import hr.fjjukic.common.model.SnackBarWithButtonUI
import hr.fjjukic.common.model.network.CategoryResponse
import hr.fjjukic.common.navigation.NavDirectionsWrapper
import hr.fjjukic.common.repository.ResourceRepository
import hr.fjjukic.common.repository.category.CategoryRepositoryImpl
import hr.fjjukic.common.utils.mapNewsResponseToCategoryArticleUI
import ht.ferit.fjjukic.app_category.R
import ht.fjjukic.category.adapter.CategoryScreenAdapter
import ht.fjjukic.category.router.CategoryRouter
import ht.fjjukic.category.view.CategoryFragmentDirections
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

class CategoryVM(
    private val repository: CategoryRepositoryImpl,
    private val resources: ResourceRepository,
    private val router: CategoryRouter
): ViewModel() {
    val screenAdapter = CategoryScreenAdapter()
    val refreshCategoryNewsSubject: ReplaySubject<ActionType> = ReplaySubject.createWithSize(1)

    private var apiRoutePath: ApiRoutePath = ApiRoutePath.NEWEST
    private var categoryId: Int = 1
    private var pagination: Pagination = Pagination()

    fun init(categoryId: Int, apiRoutePath: ApiRoutePath) {
        this.categoryId = categoryId
        this.apiRoutePath = apiRoutePath
        setCategoryNewsSubject()
    }

    private fun setCategoryNewsSubject() {
        refreshCategoryNewsSubject
            .debounce(150, TimeUnit.MILLISECONDS)
            .doOnNext { actionType ->
                screenAdapter.loader.postValue(LoaderUI(true))
                if (actionType == ActionType.REFRESH) {
                    pagination.resetPage()
                }
            }
            .flatMap { actionType ->
                when {
                    pagination.isLastPage -> {
                        Observable.just(combineNews(mutableListOf()))
                    }
                    else -> {
                        getNewsObservable().map {
                            val categoryArticles = mapNewsResponseToCategoryArticleUI(it.articles)
                            when (actionType) {
                                ActionType.REFRESH -> {
                                    pagination.current_page++
                                    categoryArticles
                                }
                                else -> {
                                    combineNews(categoryArticles)
                                }
                            }
                        }
                    }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                screenAdapter.ui.postValue(it)
                screenAdapter.loader.postValue(LoaderUI(false))
            }, {
                showError()
                screenAdapter.loader.postValue(LoaderUI(false))
            })
    }

    private fun getNewsObservable(): Observable<CategoryResponse> {
        return repository.getNews(
            CategoryHelper(
                categoryId,
                apiRoutePath,
                pagination.current_page
            )
        )
    }

    private fun combineNews(categoryArticles: MutableList<CategoryArticleUI>): MutableList<CategoryArticleUI> {
        return when {
            categoryArticles.isEmpty() -> {
                pagination.isLastPage = true
                mutableListOf()
            }
            else -> {
                val news: MutableList<CategoryArticleUI> = mutableListOf()
                screenAdapter.ui.value?.let { news.addAll(it) }
                news.addAll(categoryArticles)
                pagination.current_page++
                news
            }
        }
    }

    fun checkIsLastPage(): Boolean {
        return pagination.isLastPage
    }

    fun navigateToArticle(articleId: Int) {
        router.navigationDirections.postValue(
            NavDirectionsWrapper(
                directions = CategoryFragmentDirections.actionNavigateToArticle(
                    articleId = articleId
                ), openInNewScreen = true
            )
        )
    }

    fun pullToRefresh() {
        refreshCategoryNewsSubject.onNext(ActionType.REFRESH)
    }

    private fun showError() {
        screenAdapter.snackbar.postValue(
            SnackBarUI(
                resources.getString(R.string.error_message)
            )
        )
    }
}