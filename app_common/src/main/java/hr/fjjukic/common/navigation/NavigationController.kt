package hr.fjjukic.common.navigation

import androidx.navigation.NavController

interface NavigationController {
    val activityFragmentController: NavController
    val fragmentFragmentController: NavController


    fun navigate(navDirections: NavDirectionsWrapper) {
        val navController = getNavController(navDirections.openInNewScreen)
        navController.navigate(navDirections.directions)
    }

    fun getNavController(openInFullScreen: Boolean): NavController {
        return if (openInFullScreen) {
            activityFragmentController
        } else {
            fragmentFragmentController
        }

    }

    fun popBackStack() {
        when {
            fragmentFragmentController.previousBackStackEntry != null ->
                fragmentFragmentController.popBackStack()
            else -> activityFragmentController.popBackStack()
        }
    }
}