package com.pam.myrestaurants.data.model

import android.view.Menu
import com.google.gson.annotations.SerializedName


data class Categories (
    @SerializedName("id"         ) var id         : String,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("menu-items" ) var menuItems : ArrayList<MenuItems>,
)