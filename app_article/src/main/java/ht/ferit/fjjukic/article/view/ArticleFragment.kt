package ht.ferit.fjjukic.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.fjjukic.common.contracts.OnArticleClick
import ht.ferit.fjjukic.app_article.databinding.FragmentArticleBinding
import ht.ferit.fjjukic.article.epoxy.controller.ArticleEpoxyController
import ht.ferit.fjjukic.article.viewmodel.ArticleVM
import org.koin.android.viewmodel.ext.android.getViewModel

class ArticleFragment : Fragment() {

    private val viewModel: ArticleVM by lazy { getViewModel() }
    private lateinit var binding: FragmentArticleBinding
    private lateinit var epoxyController: ArticleEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onArticleClick = object : OnArticleClick {
            override fun navigateToArticle(articleId: Int) {
                viewModel.navigateToArticle(articleId)
            }
        }
        epoxyController = ArticleEpoxyController(onArticleClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleId = arguments?.getInt(ARTICLE_ID)
        viewModel.init(articleId)
        setRecyclerView()
        setObservers()
    }

    private fun setRecyclerView() {
        binding.epoxyRecyclerView.setController(epoxyController)
    }

    private fun setObservers() {
        viewModel.screenAdapter.articles.observe(viewLifecycleOwner, {
            it?.let {
                epoxyController.setData(it)
            }
        })
        viewModel.screenAdapter.loader.observe(viewLifecycleOwner, {
            when (it.isVisible) {
                true -> binding.loader.visibility = View.VISIBLE
                else -> binding.loader.visibility = View.GONE
            }
        })
    }

    companion object {
        const val ARTICLE_ID = "articleId"
    }
}