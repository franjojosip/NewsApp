package hr.fjjukic.common.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import hr.fjjukic.common.R

fun showErrorDialog(context: Context) {
    val builder = AlertDialog.Builder(context)

    builder.setTitle(context.getString(R.string.error))
    builder.setMessage(context.getString(R.string.error_message))
    builder.setPositiveButton(
        context.getString(R.string.positive)
    ) { dialog, _ ->
        dialog.dismiss()
    }
    builder.show()
}
