package ht.ferit.fjjukic.menu.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjjukic.common.adapter.AppScreenAdapterImpl
import hr.fjjukic.common.model.MenuUI

class MenuScreenAdapter: AppScreenAdapterImpl(){
    val menuUI by lazy { MutableLiveData<MutableList<MenuUI>>() }
}