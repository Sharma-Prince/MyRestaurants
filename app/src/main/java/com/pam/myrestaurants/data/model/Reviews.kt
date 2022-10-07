package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName


data class Reviews (

    @SerializedName("name"     ) var name     : String,
    @SerializedName("date"     ) var date     : String,
    @SerializedName("rating"   ) var rating   : Int,
    @SerializedName("comments" ) var comments : String,

)