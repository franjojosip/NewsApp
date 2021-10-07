package ht.fjjukic.category.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjjukic.common.adapter.AppScreenAdapterImpl
import hr.fjjukic.common.model.CategoryArticleUI

class CategoryScreenAdapter: AppScreenAdapterImpl() {
    val ui = MutableLiveData<MutableList<CategoryArticleUI>>()
}