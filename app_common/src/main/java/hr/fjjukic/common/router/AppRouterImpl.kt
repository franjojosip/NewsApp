package hr.fjjukic.common.router

import androidx.lifecycle.LifecycleOwner
import hr.fjjukic.common.livedata.NavigationEvent
import hr.fjjukic.common.navigation.NavDirectionsWrapper
import hr.fjjukic.common.navigation.NavigationComponentController
import hr.fjjukic.common.navigation.NavigationController

open class AppRouterImpl : AppRouter {
    override val navigationDirections: NavigationEvent<NavDirectionsWrapper>
        get() = TODO("Not yet implemented")
    override val backCallback: NavigationEvent<Unit>
        get() = TODO("Not yet implemented")
    override val navigationEvent: NavigationEvent<NavDirectionsWrapper>
        get() = TODO("Not yet implemented")

    override fun observe(lifecycleOwner: LifecycleOwner, controller: NavigationController) {
        super.observe(lifecycleOwner, controller)
        (controller as? NavigationComponentController)?.apply {
            openScreen(lifecycleOwner, this)
        }
    }

    private fun openScreen(
        lifecycleOwner: LifecycleOwner,
        controller: NavigationComponentController
    ) {
        navigationDirections.observe(lifecycleOwner) {
            controller.navigate(it)
        }
    }
}