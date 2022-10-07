package com.pam.myrestaurants.domain.repository


import com.pam.myrestaurants.data.model.MenuResult
import com.pam.myrestaurants.data.model.RestaurantsResult

interface RestaurantSearchRepository {
    suspend fun getRestaurantSearch(s:String) : RestaurantsResult
    suspend fun getMenuResult(s: String) : MenuResult
}