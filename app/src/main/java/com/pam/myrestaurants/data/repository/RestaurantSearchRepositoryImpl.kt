package com.pam.myrestaurants.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pam.myrestaurants.common.Constants
import com.pam.myrestaurants.data.model.MenuResult
import com.pam.myrestaurants.data.model.RestaurantsResult
import com.pam.myrestaurants.domain.repository.RestaurantSearchRepository
import com.pam.myrestaurants.utils.JsonHelper

class RestaurantSearchRepositoryImpl(private val jsonHelper: JsonHelper) : RestaurantSearchRepository {
    override suspend fun getRestaurantSearch(s: String): RestaurantsResult {
        val jsonString =  jsonHelper.readJsonAssetsFile(s)
        val listType = object : TypeToken<RestaurantsResult>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    override suspend fun getMenuResult(s: String): MenuResult {
        val jsonString =  jsonHelper.readJsonAssetsFile(s)
        val listType = object : TypeToken<MenuResult>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}