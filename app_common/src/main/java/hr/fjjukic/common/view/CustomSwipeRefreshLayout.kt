package hr.fjjukic.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hr.fjjukic.common.R

class CustomSwipeRefreshLayout : SwipeRefreshLayout {

    constructor(@NonNull context: Context) : super(context) {
        setColors()
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(
        context, attrs
    ) {
        setColors()
    }

    private fun setColors() {
        setColorSchemeColors(ContextCompat.getColor(context, R.color.red))
    }
}