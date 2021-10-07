package ht.fjjukic.category.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import hr.fjjukic.common.utils.CategoryTabUtil
import ht.ferit.fjjukic.app_category.R
import ht.ferit.fjjukic.app_category.databinding.FragmentCategoryBinding
import ht.fjjukic.category.adapter.CategoryAdapter

class CategoryFragment : Fragment(R.layout.fragment_category) {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
    }

    private fun setViewPager() {
        binding.categoryViewPager.apply {
            adapter = categoryAdapter
            binding.tabLayout.setupWithViewPager(this)
        }
    }

    private fun createAdapter() {
        val tabList = CategoryTabUtil.getCategoryTabList(arguments?.getInt(CATEGORY_ID) ?: 1)
            .toMutableList()
        tabList.map { tabHelper -> tabHelper.tabName = getString(tabHelper.tabNameId) }
        categoryAdapter = CategoryAdapter(
            tabList, childFragmentManager
        )
    }

    companion object {
        const val CATEGORY_ID = "categoryId"
        fun newInstance(categoryId: Int) = CategoryFragment().apply {
            arguments = bundleOf(
                CATEGORY_ID to categoryId
            )
        }
    }
}