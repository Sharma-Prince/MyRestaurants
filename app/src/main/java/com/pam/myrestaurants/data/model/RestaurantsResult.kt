package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName


data class RestaurantsResult (
    @SerializedName("restaurants" )
    var restaurants : ArrayList<Restaurant>

)