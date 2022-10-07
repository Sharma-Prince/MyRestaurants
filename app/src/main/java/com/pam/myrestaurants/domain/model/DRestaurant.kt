package com.pam.myrestaurants.domain.model

import com.google.gson.annotations.SerializedName

data class DRestaurant (
    @SerializedName("id"              ) var id             : Int,
    @SerializedName("name"            ) var name           : String,
    @SerializedName("cuisine_type"    ) var cuisineType    : String,
    @SerializedName("menu_items"      ) var menuItems      : String = "",
    @SerializedName("address"         ) var address        : String,
    @SerializedName("rating"          ) var rating         : Float,
    @SerializedName("photograph"      ) var imgUrl         : String,
)