package ht.ferit.fjjukic.menu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.fjjukic.common.events.SnackBarUI
import hr.fjjukic.common.repository.ResourceRepository
import hr.fjjukic.common.repository.menu.MenuRepositoryImpl
import hr.fjjukic.common.utils.mapMenuResponseToMenuUI
import ht.ferit.fjjukic.menu.R
import ht.ferit.fjjukic.menu.adapter.MenuScreenAdapter
import ht.ferit.fjjukic.menu.router.MenuRouter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MenuVM(
    private val repository: MenuRepositoryImpl,
    private val resources: ResourceRepository
) : ViewModel() {
    val screenAdapter = MenuScreenAdapter()
    var position: MutableLiveData<Int> = MutableLiveData()

    fun setMenuDisposable() {
        repository.getMenu()
            .map {
                mapMenuResponseToMenuUI(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                screenAdapter.menuUI.postValue(it)
            }, {
                showError()
            })
    }

    fun navigateToCategory(categoryId: Int) {
        screenAdapter.menuUI.value?.let { menu ->
            val position = menu.map { item -> item.categoryId }.indexOf(categoryId)
            if (position != -1) {
                this.position.postValue(position)
            }
        }
    }

    private fun showError() {
        screenAdapter.snackbar.postValue(SnackBarUI(resources.getString(R.string.error_message)))
    }
}