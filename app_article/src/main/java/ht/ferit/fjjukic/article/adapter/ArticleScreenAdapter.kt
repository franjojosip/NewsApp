package ht.ferit.fjjukic.article.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjjukic.common.adapter.AppScreenAdapterImpl
import hr.fjjukic.common.enums.ArticleViewHolderType

class ArticleScreenAdapter: AppScreenAdapterImpl() {
    val articles = MutableLiveData<MutableList<ArticleViewHolderType>>()
}