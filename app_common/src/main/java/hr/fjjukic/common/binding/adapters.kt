package hr.fjjukic.common.binding

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import hr.fjjukic.common.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(imageUrl)
            .error(R.drawable.placeholder)
            .into(this)
    }
    else{
        Glide.with(this.context)
            .load(R.drawable.placeholder)
            .into(this)
    }
}

@BindingAdapter("isVisible")
fun TextView.isVisible(text: String?) {
    if (!text.isNullOrEmpty()) {
        this.visibility = View.VISIBLE
    }
    else this.visibility = View.GONE
}

@BindingAdapter("backgroundShape")
fun TextView.setBackgroundShape(color: String?) {
    if (!color.isNullOrEmpty()) {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 8f
        shape.setColor(Color.parseColor(color))
        this.background = shape
    }
    else this.background = null
}

