package ht.fjjukic.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.fjjukic.common.events.LoaderUI
import hr.fjjukic.common.events.SnackBarUI
import hr.fjjukic.common.navigation.NavDirectionsWrapper
import hr.fjjukic.common.repository.ResourceRepository
import hr.fjjukic.common.repository.news.NewsRepositoryImpl
import hr.fjjukic.common.utils.mapNewsResponseToNewsList
import ht.ferit.fjjukic.app_home.R
import ht.fjjukic.home.adapter.IndexScreenAdapter
import ht.fjjukic.home.router.IndexRouter
import ht.fjjukic.home.view.IndexFragmentDirections
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

class IndexVM(
    private val repository: NewsRepositoryImpl,
    private val resources: ResourceRepository,
    private val router: IndexRouter
) : ViewModel() {
    val screenAdapter = IndexScreenAdapter()
    var categoryId: MutableLiveData<Int> = MutableLiveData()
    val refreshIndexNewsSubject: ReplaySubject<Boolean> = ReplaySubject.createWithSize(1)

    fun init() {
        setIndexNewsSubject()
    }

    private fun setIndexNewsSubject() {
        refreshIndexNewsSubject
            .debounce(150, TimeUnit.MILLISECONDS)
            .doOnNext {
                screenAdapter.loader.postValue(LoaderUI(true))
            }
            .flatMap { repository.getIndexNews() }
            .map {
                mapNewsResponseToNewsList(it)
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

    private fun showError() {
        screenAdapter.snackbar.postValue(SnackBarUI(resources.getString(R.string.error_message)))
    }

    fun navigateToCategory(categoryId: Int) {
        this.categoryId.postValue(categoryId)
    }

    fun navigateToArticle(articleId: Int) {
        router.navigationDirections.postValue(
            NavDirectionsWrapper(
                directions = IndexFragmentDirections.actionNavigateToArticle(
                    articleId = articleId
                ), openInNewScreen = true
            )
        )
    }

    fun pullToRefresh() {
        refreshIndexNewsSubject.onNext(true)
    }
}