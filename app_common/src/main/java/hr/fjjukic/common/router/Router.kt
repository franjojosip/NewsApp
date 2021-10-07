package hr.fjjukic.common.router

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import hr.fjjukic.common.livedata.NavigationEvent
import hr.fjjukic.common.navigation.NavDirectionsWrapper
import hr.fjjukic.common.navigation.NavigationController

interface OnBackListener {
    fun navigateUp()
}

interface Router : OnBackListener {
    val backCallback: NavigationEvent<Unit>
    val navigationEvent: NavigationEvent<NavDirectionsWrapper>

    fun observe(lifecycleOwner: LifecycleOwner, controller: NavigationController) {
        setBackNavigationEventObserver(lifecycleOwner, controller)
        setNavigationEventObserver(lifecycleOwner, controller)
    }

    override fun navigateUp() {
        backCallback.call()
    }

    fun setBackNavigationEventObserver(
        viewLifecycleOwner: LifecycleOwner,
        controller: NavigationController
    ) {
        backCallback.observeWith(viewLifecycleOwner) {
            onBackEvent(controller)
        }
    }

    fun setNavigationEventObserver(
        viewLifecycleOwner: LifecycleOwner,
        controller: NavigationController,
    ) {
        navigationEvent.observeWith(viewLifecycleOwner) { navDirections ->
            if (navDirections != null) {
                controller.navigate(navDirections)
            }
        }
    }

    fun onBackEvent(controller: NavigationController) {
        controller.popBackStack()
    }
}

fun <T> LiveData<T>.observeWith(viewLifecycleOwner: LifecycleOwner, callDelegate:(T?)->Unit){
    this.observe(viewLifecycleOwner, {
        callDelegate(it)
    })
}