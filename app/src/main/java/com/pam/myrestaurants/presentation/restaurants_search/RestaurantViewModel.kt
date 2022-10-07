package com.pam.myrestaurants.presentation.restaurants_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.myrestaurants.common.Resource
import com.pam.myrestaurants.domain.use_case.SearchRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel @Inject constructor(private val searchRestaurantUseCase: SearchRestaurantUseCase) : ViewModel() {
    private val _restaurantSearchList = MutableStateFlow<RestaurantSearchState>(RestaurantSearchState())
    val restaurantSearchList: StateFlow<RestaurantSearchState> = _restaurantSearchList

    fun getRestaurantList() {
        searchRestaurantUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _restaurantSearchList.value = RestaurantSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _restaurantSearchList.value = RestaurantSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _restaurantSearchList.value = RestaurantSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}