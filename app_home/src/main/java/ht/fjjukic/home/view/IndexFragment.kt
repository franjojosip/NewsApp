package ht.fjjukic.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.fjjukic.common.contracts.OnCategoryClick
import hr.fjjukic.common.contracts.OnNavigateClick
import ht.ferit.fjjukic.app_home.R
import ht.ferit.fjjukic.app_home.databinding.FragmentIndexBinding
import ht.fjjukic.home.epoxy.controller.IndexEpoxyController
import ht.fjjukic.home.viewmodel.IndexVM
import org.koin.android.viewmodel.ext.android.getViewModel

class IndexFragment : Fragment() {
    private val viewModel: IndexVM by lazy { getViewModel() }
    private var listener: OnCategoryClick? = null
    private lateinit var binding: FragmentIndexBinding
    private lateinit var epoxyController: IndexEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onNavigateClick = object : OnNavigateClick {
            override fun navigateToCategory(categoryId: Int) {
                viewModel.navigateToCategory(categoryId)
            }

            override fun navigateToArticle(articleId: Int) {
                viewModel.navigateToArticle(articleId)
            }
        }
        epoxyController = IndexEpoxyController(onNavigateClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndexBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        setRecyclerView()
        setObservers()
        setSwipeRefreshLayout()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment != null && parentFragment is OnCategoryClick) {
            listener = parentFragment as OnCategoryClick
        } else {
            throw RuntimeException(getString(R.string.required_listener, "OnCategoryClick"))
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshIndexNewsSubject.onNext(true)
    }

    private fun setRecyclerView() {
        binding.epoxyRecyclerView.setController(epoxyController)
    }

    private fun setObservers() {
        viewModel.screenAdapter.ui.observe(viewLifecycleOwner, {
            epoxyController.setData(it)
        })
        viewModel.categoryId.observe(viewLifecycleOwner, {
            it?.let { categoryId ->
                listener?.navigateToCategory(categoryId)
            }
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

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.pullToRefresh()
        }
    }

    companion object {
        fun newInstance() = IndexFragment()
    }
}