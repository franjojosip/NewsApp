package hr.fjjukic.common.fragment

import hr.fjjukic.common.events.Event
import hr.fjjukic.common.events.SnackBarUI
import hr.fjjukic.common.events.ToastUI

interface FragmentDelegate {
    fun toast(toastUI: ToastUI)
    fun snackbar(snackbarUI: SnackBarUI)
    fun showKeyboard()
    fun hideKeyboard()
    fun setEvent(event: Event) {

    }

    fun showLoader() {

    }

    fun hideLoader() {

    }
}