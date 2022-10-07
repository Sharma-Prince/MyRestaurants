package com.pam.myrestaurants.data.model


import com.google.gson.annotations.SerializedName

data class MenuResult (
    @SerializedName("menus" ) var menusList : ArrayList<Menus>,
)