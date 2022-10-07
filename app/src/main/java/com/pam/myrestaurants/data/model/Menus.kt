package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName




data class Menus (
    @SerializedName("restaurantId" ) var restaurantId : Int,
    @SerializedName("categories"   ) var categories   : ArrayList<Categories>,
)