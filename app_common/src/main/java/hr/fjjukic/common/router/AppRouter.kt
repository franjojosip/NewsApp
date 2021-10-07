package hr.fjjukic.common.router

import hr.fjjukic.common.livedata.NavigationEvent
import hr.fjjukic.common.navigation.NavDirectionsWrapper

interface AppRouter : Router {
    val navigationDirections: NavigationEvent<NavDirectionsWrapper>
}