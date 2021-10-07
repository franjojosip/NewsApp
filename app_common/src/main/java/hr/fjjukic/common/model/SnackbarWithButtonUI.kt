package hr.fjjukic.common.model

import hr.fjjukic.common.events.SnackBarUI

class SnackBarWithButtonUI(
    message: String,
    val actionText: String = "",
    val callback: () -> Unit = {}
) : SnackBarUI(message)