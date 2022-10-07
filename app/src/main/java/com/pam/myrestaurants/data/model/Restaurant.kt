package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName
import com.pam.myrestaurants.domain.model.DRestaurant


data class Restaurant (

    @SerializedName("id"              ) var id             : Int,
    @SerializedName("name"            ) var name           : String,
    @SerializedName("neighborhood"    ) var neighborhood   : String,
    @SerializedName("photograph"      ) var photograph     : String,
    @SerializedName("address"         ) var address        : String,
    @SerializedName("latlng"          ) var latlng         : Latlng,
    @SerializedName("cuisine_type"    ) var cuisineType    : String,
    @SerializedName("operating_hours" ) var operatingHours : OperatingHours,
    @SerializedName("reviews"         ) var reviews        : ArrayList<Reviews>

)

fun Restaurant.toDomainDRestaurant(rating : Float): DRestaurant {
    return DRestaurant(
        id = this.id,
        name = this.name ?: "",
        cuisineType = this.cuisineType ?: "",
        menuItems = "",
        address = this.address ?: "",
        rating = rating ?: 0.0f,
        imgUrl = this.photograph ?: ""
    )
}