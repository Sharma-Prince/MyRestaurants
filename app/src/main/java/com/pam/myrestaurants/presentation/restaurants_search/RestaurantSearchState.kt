package com.pam.myrestaurants.presentation.restaurants_search


import com.pam.myrestaurants.domain.model.DRestaurant

data class RestaurantSearchState (
    val isLoading: Boolean = false,
    val data: List<DRestaurant>? = null,
    val error: String = ""
)