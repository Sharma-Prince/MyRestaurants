package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName


data class MenuItems (
    @SerializedName("id"          ) var id          : String,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("description" ) var description : String,
    @SerializedName("price"       ) var price       : String,
    @SerializedName("images"      ) var images      : ArrayList<String>,
)