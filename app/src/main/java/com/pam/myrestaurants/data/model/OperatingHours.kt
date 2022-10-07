package com.pam.myrestaurants.data.model

import com.google.gson.annotations.SerializedName

data class OperatingHours (

    @SerializedName("Monday"    ) var Monday    : String,
    @SerializedName("Tuesday"   ) var Tuesday   : String,
    @SerializedName("Wednesday" ) var Wednesday : String,
    @SerializedName("Thursday"  ) var Thursday  : String,
    @SerializedName("Friday"    ) var Friday    : String,
    @SerializedName("Saturday"  ) var Saturday  : String,
    @SerializedName("Sunday"    ) var Sunday    : String,
)