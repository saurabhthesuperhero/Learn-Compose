package com.saurabhjadhav.learncompose

import androidx.annotation.DrawableRes

sealed class Screen(val route: String, val title : String, @DrawableRes val icon : Int) {
    object Home : Screen(route = "home", title = "Home", icon= R.drawable.ic_baseline_home_24)
    object Search : Screen( route = "search", title = "Search", icon= R.drawable.ic_baseline_search_24)
    object Profile : Screen(route = "profile", title = "Profile", icon= R.drawable.ic_baseline_account_circle_24)
}