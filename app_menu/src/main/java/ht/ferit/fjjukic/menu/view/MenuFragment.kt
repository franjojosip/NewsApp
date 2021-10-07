package ht.ferit.fjjukic.menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.fjjukic.common.contracts.OnCategoryClick
import ht.ferit.fjjukic.menu.adapter.MenuPagerAdapter
import ht.ferit.fjjukic.menu.databinding.FragmentMenuBinding
import ht.ferit.fjjukic.menu.viewmodel.MenuVM
import org.koin.android.viewmodel.ext.android.getViewModel

class MenuFragment : Fragment(), OnCategoryClick {

    private val viewModel: MenuVM by lazy { getViewModel() }
    private lateinit var binding: FragmentMenuBinding
    private lateinit var menuPagerAdapter: MenuPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuPagerAdapter = MenuPagerAdapter(mutableListOf(), childFragmentManager)
        viewModel.setMenuDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setObserver()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.screenAdapter.menuUI.value.isNullOrEmpty()) {
            viewModel.setMenuDisposable()
        }
    }

    private fun setViewPager() {
        binding.viewPager.apply {
            adapter = menuPagerAdapter
            binding.tabLayout.setupWithViewPager(this)
        }
    }

    private fun setObserver() {
        viewModel.screenAdapter.menuUI.observe(viewLifecycleOwner, {
            menuPagerAdapter.setData(it)
        })
        viewModel.position.observe(viewLifecycleOwner, {
            it?.let { position ->
                if (position != -1) {
                    binding.viewPager.currentItem = it
                }
            }
        })
    }

    override fun navigateToCategory(categoryId: Int) {
        viewModel.navigateToCategory(categoryId)
    }
}