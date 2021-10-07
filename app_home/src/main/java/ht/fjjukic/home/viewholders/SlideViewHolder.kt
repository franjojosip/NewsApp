package ht.fjjukic.home.viewholders

import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import hr.fjjukic.common.adapter.RecycledPagerAdapter
import hr.fjjukic.common.binding.setBackgroundShape
import hr.fjjukic.common.contracts.OnNavigateClick
import hr.fjjukic.common.model.SlideUI
import ht.ferit.fjjukic.app_home.databinding.CellSlideBinding

class SlideViewHolder(val view: View, private val onNavigateClick: OnNavigateClick) :
    RecycledPagerAdapter.ViewHolder(view) {
    private val binding = CellSlideBinding.bind(view)

    fun bind(slideUI: SlideUI) {
        Glide.with(view.context).load(slideUI.imageUrl).into(binding.ivSlideArticle)
        binding.tvSlideArticleCategory.text =
            HtmlCompat.fromHtml(slideUI.category.name, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvSlideArticleTitle.text =
            HtmlCompat.fromHtml(slideUI.title, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.tvSlideArticleCategory.setBackgroundShape(slideUI.category.color)

        view.setOnClickListener {
            onNavigateClick.navigateToArticle(slideUI.articleId)
        }
    }
}