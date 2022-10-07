package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName


data class Latlng (

    @SerializedName("lat" ) var lat : Double,
    @SerializedName("lng" ) var lng : Double,

)