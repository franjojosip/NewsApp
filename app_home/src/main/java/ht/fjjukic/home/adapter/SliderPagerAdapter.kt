package ht.fjjukic.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import hr.fjjukic.common.adapter.RecycledPagerAdapter
import hr.fjjukic.common.contracts.OnNavigateClick
import hr.fjjukic.common.enums.ViewHolderType
import hr.fjjukic.common.model.SlideUI
import ht.ferit.fjjukic.app_home.R
import ht.fjjukic.home.viewholders.SlideViewHolder


class SliderPagerAdapter(private var news: MutableList<SlideUI>, private val onNavigateClick: OnNavigateClick): RecycledPagerAdapter<RecycledPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return SlideViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.cell_slide, parent, false), onNavigateClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        (viewHolder as SlideViewHolder).bind(news[position])
    }

    override fun getItemViewType(position: Int): Int {
        return ViewHolderType.SLIDER.ordinal
    }

    override fun getCount(): Int {
        return news.size
    }

    fun setNews(news: List<SlideUI>){
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }

}