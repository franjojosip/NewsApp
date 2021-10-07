package hr.fjjukic.common.adapter
import android.view.View
import android.view.ViewGroup

import androidx.viewpager.widget.PagerAdapter

@Suppress("UNCHECKED_CAST")
abstract class RecycledPagerAdapter<VH : RecycledPagerAdapter.ViewHolder?> :
    PagerAdapter() {
    abstract class ViewHolder(val itemView: View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewHolder = onCreateViewHolder(container, getItemViewType(position)) as VH
        onBindViewHolder(viewHolder, position)
        container.addView(viewHolder!!.itemView)
        return viewHolder
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object` as VH)!!.itemView)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as VH)!!.itemView === view
    }

    /**
     * Create a new view holder
     * @param parent
     * @return view holder
     */
    abstract fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder

    /**
     * Bind data at position into viewHolder
     * @param viewHolder
     * @param position
     */
    abstract fun onBindViewHolder(viewHolder: VH, position: Int)

    /**
     * get Item Type
     * @param position
     */
    abstract fun getItemViewType(position: Int): Int
}