package hr.fjjukic.common.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs


class CustomViewPager(context: Context, attrs: AttributeSet?) :
    ViewPager(context, attrs) {
    private val bufferY = 10
    private var lastX = 0f
    private var lastY = 0f
    private var startDragX = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                startDragX = ev.x
                requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.x - lastX) > abs(ev.y - lastY)) {
                    if (startDragX < ev.x && currentItem == 0 || startDragX > ev.x && currentItem == adapter!!.count - 1) {
                        requestDisallowInterceptTouchEvent(false)
                    }
                    else{
                        requestDisallowInterceptTouchEvent(true)
                    }
                } else if (abs(ev.y - lastY) > bufferY) {
                    requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        lastX = ev.x
        lastY = ev.y
        return super.onTouchEvent(ev)
    }
}