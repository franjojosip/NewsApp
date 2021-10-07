package ht.fjjukic.home.epoxy.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import hr.fjjukic.common.contracts.OnNavigateClick
import hr.fjjukic.common.model.SlideUI
import ht.ferit.fjjukic.app_home.R
import ht.ferit.fjjukic.app_home.databinding.CellSliderBinding
import ht.fjjukic.home.adapter.SliderPagerAdapter

@EpoxyModelClass
abstract class SliderEpoxyModel: DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var listener: OnNavigateClick

    @EpoxyAttribute
    lateinit var data: MutableList<SlideUI>

    override fun getDefaultLayout(): Int = R.layout.cell_slider

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        if(binding is CellSliderBinding){
            binding.viewPagerSlider.adapter = SliderPagerAdapter(data, listener)
            binding.dotsIndicator.setViewPager(binding.viewPagerSlider)
        }
    }
}