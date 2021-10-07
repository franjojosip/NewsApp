package hr.fjjukic.common.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import hr.fjjukic.common.events.*
import hr.fjjukic.common.fragment.FragmentDelegate
import hr.fjjukic.common.livedata.SingleLiveData

interface AppScreenAdapter {
    val loader: SingleLiveData<LoaderUI>
    val toast: SingleLiveData<ToastUI>
    val keyboard: SingleLiveData<KeyboardEventUI>
    val snackbar: SingleLiveData<SnackBarUI>

    fun observe(lifecycleOwner: LifecycleOwner, fragmentDelegate: FragmentDelegate) {
        setToastObserver(lifecycleOwner, fragmentDelegate)
        setKeyboardObserver(lifecycleOwner, fragmentDelegate)
        setLoaderObserver(lifecycleOwner, fragmentDelegate)
        setSnackbarObserver(lifecycleOwner, fragmentDelegate)
    }

    fun setToastObserver(lifecycleOwner: LifecycleOwner, fragmentDelegate: FragmentDelegate) {
        toast.observeWithNotNull(lifecycleOwner, fragmentDelegate::toast)
    }

    fun setKeyboardObserver(lifecycleOwner: LifecycleOwner, fragmentDelegate: FragmentDelegate) {
        keyboard.observeWithNotNull(lifecycleOwner) {
            if (it.isVisible)
                fragmentDelegate.showKeyboard()
            else
                fragmentDelegate.hideKeyboard()
        }
    }

    fun setLoaderObserver(lifecycleOwner: LifecycleOwner, fragmentDelegate: FragmentDelegate) {
        loader.observeWithNotNull(lifecycleOwner) {
            if (it.isVisible)
                fragmentDelegate.showLoader()
            else
                fragmentDelegate.hideLoader()
        }
    }

    fun setSnackbarObserver(lifecycleOwner: LifecycleOwner, fragmentDelegate: FragmentDelegate) {
        snackbar.observeWithNotNull(lifecycleOwner, fragmentDelegate::snackbar)
    }

    fun <T> LiveData<T>.observeWithNotNull(
        viewLifecycleOwner: LifecycleOwner,
        callDelegate: (T) -> Unit
    ) {
        this.observe(viewLifecycleOwner, {
            it?.run(callDelegate)
        })
    }
}

open class AppScreenAdapterImpl : AppScreenAdapter {
    override val loader by lazy { SingleLiveData<LoaderUI>() }
    override val toast by lazy { SingleLiveData<ToastUI>() }
    override val snackbar by lazy { SingleLiveData<SnackBarUI>() }
    override val keyboard by lazy { SingleLiveData<KeyboardEventUI>() }
}