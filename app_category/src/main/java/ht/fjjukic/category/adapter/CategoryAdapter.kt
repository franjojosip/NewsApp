package ht.fjjukic.category.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import hr.fjjukic.common.helpers.CategoryTabHelper
import ht.fjjukic.category.view.CategoryNewsFragment

class CategoryAdapter(
    private var tabs: MutableList<CategoryTabHelper>,
    manager: FragmentManager
) : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return tabs.size
    }
    override fun getPageTitle(position: Int): CharSequence {
        return tabs[position].tabName
    }
    override fun getItem(position: Int): Fragment {
        return CategoryNewsFragment.newInstance(tabs[position].categoryId, tabs[position].apiRoutePathIndex)
    }
}