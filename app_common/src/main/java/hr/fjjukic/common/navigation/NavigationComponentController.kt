package hr.fjjukic.common.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import hr.fjjukic.common.R

open class NavigationComponentController(protected val fragment: Fragment) :
    NavigationController {
    override val activityFragmentController
        get() = Navigation.findNavController(
            fragment.requireActivity(),
            R.id.nav_host_fragment
        )
    override val fragmentFragmentController get() = fragment.findNavController()

    override fun navigate(navigationTarget: NavDirectionsWrapper) {
        handleNavigationComponentDirections(navigationTarget)
    }

    fun navigateUp() {
        fragment.findNavController().navigateUp()
    }

    fun navigateUpTo(tag: String, flag: Int) {
        fragment.findNavController().navigateUp()
    }

    protected open fun handleNavigationComponentDirections(navDirections: NavDirectionsWrapper) {
        val controller = if (navDirections.openInNewScreen) {
            activityFragmentController
        } else {
            fragmentFragmentController
        }
        controller.navigate(navDirections.directions)
    }
}