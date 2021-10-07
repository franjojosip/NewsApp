package ht.ferit.fjjukic.article.viewmodel

import androidx.lifecycle.ViewModel
import hr.fjjukic.common.events.LoaderUI
import hr.fjjukic.common.events.SnackBarUI
import hr.fjjukic.common.navigation.NavDirectionsWrapper
import hr.fjjukic.common.repository.ResourceRepository
import hr.fjjukic.common.repository.article.ArticleRepositoryImpl
import hr.fjjukic.common.utils.mapSingleArticleResponseToArticleList
import ht.ferit.fjjukic.app_article.R
import ht.ferit.fjjukic.article.adapter.ArticleScreenAdapter
import ht.ferit.fjjukic.article.router.ArticleRouter
import ht.ferit.fjjukic.article.view.ArticleFragmentDirections
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticleVM(
    private val repository: ArticleRepositoryImpl,
    private val resources: ResourceRepository,
    private val router: ArticleRouter,
) : ViewModel() {
    val screenAdapter = ArticleScreenAdapter()
    private var compositeDisposable = CompositeDisposable()
    private var articleId: Int = 1
    private var page: Int = 1

    fun init(articleId: Int?) {
        articleId?.let {
            this.articleId = it
            loadArticle()
        }
    }

    private fun loadArticle() {
        repository.getArticle(articleId, page)
            .doOnNext { screenAdapter.loader.postValue(LoaderUI(true)) }
            .map {
                mapSingleArticleResponseToArticleList(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                screenAdapter.articles.postValue(it)
                screenAdapter.loader.postValue(LoaderUI(false))
            },{
                showError()
                screenAdapter.loader.postValue(LoaderUI(false))
            })
    }

    fun navigateToArticle(articleId: Int) {
        router.navigationDirections.postValue(
            NavDirectionsWrapper(
                directions = ArticleFragmentDirections.actionNavigateToArticle(
                    articleId = articleId
                ), openInNewScreen = true
            )
        )
    }

    private fun showError() {
        screenAdapter.snackbar.postValue(SnackBarUI(resources.getString(R.string.error_message)))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}