package hr.fjjukic.common.navigation

import androidx.navigation.NavDirections

data class NavDirectionsWrapper(val directions:NavDirections, val openInNewScreen: Boolean = false)