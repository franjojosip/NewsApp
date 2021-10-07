package hr.fjjukic.common.events

import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import hr.fjjukic.common.R

interface Event
open class KeyboardEventUI(var isVisible: Boolean) : Event
data class LoaderUI(
    var isVisible: Boolean,
    val loadingLabel: String? = null
) : Event

open class ToastUI(
    val message: String,
    val showDuration: Int = LENGTH_SHORT
) : Event

open class SnackBarUI(
    message: String,
    val backgroundColor: Int = R.color.design_default_color_primary
) : ToastUI(message, Snackbar.LENGTH_SHORT)