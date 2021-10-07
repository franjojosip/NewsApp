package ht.fjjukic.category.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fjjukic.common.contracts.OnArticleClick
import hr.fjjukic.common.enums.ActionType
import hr.fjjukic.common.enums.ApiRoutePath
import ht.ferit.fjjukic.app_category.databinding.FragmentCategoryNewsBinding
import ht.fjjukic.category.epoxy.controller.CategoryArticleEpoxyController
import ht.fjjukic.category.viewmodel.CategoryVM
import org.koin.android.viewmodel.ext.android.getViewModel

class CategoryNewsFragment : Fragment() {
    private val viewModel: CategoryVM by lazy { getViewModel() }
    private lateinit var binding: FragmentCategoryNewsBinding
    private lateinit var epoxyController: CategoryArticleEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onArticleClick = object : OnArticleClick {
            override fun navigateToArticle(articleId: Int) {
                viewModel.navigateToArticle(articleId)
            }
        }
        epoxyController = CategoryArticleEpoxyController(onArticleClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = arguments?.getInt(CATEGORY_ID) ?: 1
        viewModel.init(categoryId, getApiRoutePath())
        setRecyclerView()
        setObservers()
        setSwipeRefreshLayout()
    }

    private fun getApiRoutePath(): ApiRoutePath {
        val apiRoutePathIndex =
            arguments?.getInt(API_ROUTE_PATH_INDEX) ?: ApiRoutePath.NEWEST.ordinal
        return ApiRoutePath.values()[apiRoutePathIndex]
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshCategoryNewsSubject.onNext(ActionType.REFRESH)
    }

    private fun setObservers() {
        viewModel.screenAdapter.ui.observe(viewLifecycleOwner, {
            epoxyController.setData(it)
        })
        viewModel.screenAdapter.loader.observe(viewLifecycleOwner, {
            when (it.isVisible) {
                true -> binding.loader.visibility = View.VISIBLE
                else -> {
                    binding.loader.visibility = View.GONE
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })
    }

    private fun setRecyclerView() {
        binding.epoxyRecyclerView.setController(epoxyController)
        binding.epoxyRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount: Int = layoutManager.itemCount
                val lastVisibleItemPosition =
                    layoutManager.findLastCompletelyVisibleItemPosition() + 1
                val isLoading = viewModel.screenAdapter.loader.value?.isVisible ?: false

                if (!viewModel.checkIsLastPage() && lastVisibleItemPosition == totalItemCount && (!isLoading)) {
                    viewModel.refreshCategoryNewsSubject.onNext(ActionType.LOAD)
                }
            }
        })
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.pullToRefresh()
        }
    }

    companion object {
        const val CATEGORY_ID = "categoryId"
        const val API_ROUTE_PATH_INDEX = "apiRoutePathIndex"

        fun newInstance(categoryId: Int, apiRoutePathIndex: Int) = CategoryNewsFragment().apply {
            arguments = bundleOf(
                CATEGORY_ID to categoryId,
                API_ROUTE_PATH_INDEX to apiRoutePathIndex
            )
        }
    }
}