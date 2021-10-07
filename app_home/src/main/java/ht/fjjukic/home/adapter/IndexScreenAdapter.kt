package ht.fjjukic.home.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjjukic.common.adapter.AppScreenAdapterImpl
import hr.fjjukic.common.enums.IndexViewHolderType

class IndexScreenAdapter: AppScreenAdapterImpl() {
    val ui = MutableLiveData<MutableList<IndexViewHolderType>>()
}