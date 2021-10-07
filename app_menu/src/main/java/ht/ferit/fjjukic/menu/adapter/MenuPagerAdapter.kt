package ht.ferit.fjjukic.menu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.fjjukic.common.enums.FragmentType
import hr.fjjukic.common.model.MenuUI
import ht.fjjukic.category.view.CategoryFragment
import ht.fjjukic.category.view.FavoriteFragment
import ht.fjjukic.home.view.IndexFragment

class MenuPagerAdapter(
    private var menu: MutableList<MenuUI>,
    manager: FragmentManager
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return menu.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return menu[position].title
    }

    override fun getItem(position: Int): Fragment {
        return when (menu[position].fragmentType) {
            FragmentType.INDEX -> IndexFragment.newInstance()
            FragmentType.FAVORITE -> FavoriteFragment.newInstance()
            FragmentType.CATEGORY -> CategoryFragment.newInstance(menu[position].categoryId)
            else -> throw RuntimeException("No fragment for given fragment type")
        }
    }

    fun setData(menu: List<MenuUI>) {
        this.menu.clear()
        this.menu.addAll(menu)
        notifyDataSetChanged()
    }
}