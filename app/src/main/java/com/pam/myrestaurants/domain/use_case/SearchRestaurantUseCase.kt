package com.pam.myrestaurants.domain.use_case

import com.pam.myrestaurants.common.Constants
import com.pam.myrestaurants.common.Resource
import com.pam.myrestaurants.data.model.Menus
import com.pam.myrestaurants.data.model.Restaurant
import com.pam.myrestaurants.data.model.toDomainDRestaurant
import com.pam.myrestaurants.domain.model.DRestaurant
import com.pam.myrestaurants.domain.repository.RestaurantSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRestaurantUseCase @Inject constructor(private val repository: RestaurantSearchRepository) {

    operator fun invoke(): Flow<Resource<List<DRestaurant>>> = flow {
        try {
            emit(Resource.Loading())
            val domainRestaurants = repository.getRestaurantSearch(Constants.RESTAURANTS)
            val domainMenus = repository.getMenuResult(Constants.MENUS);
            val restaurants = domainRestaurants.restaurants.toList().ifEmpty { emptyList() }
            val menus = domainMenus.menusList.toList().ifEmpty { emptyList() }
            val domainData = combineTwoJsonFile(restaurants,menus).toList().ifEmpty { emptyList() }
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {

        }
    }

    /**
     * Combine the  Restaurant and Menus file in one to display
     */
    private fun combineTwoJsonFile(restaurants: List<Restaurant>, menus: List<Menus>) : List<DRestaurant>{
        val hashMp : HashMap<Int,DRestaurant> = HashMap()

        val list : ArrayList<DRestaurant> = arrayListOf()

        restaurants.forEachIndexed{ _, item ->
            var rating = 0.0F
            item.reviews.forEach {
                rating += it.rating
            }
            rating/=item.reviews.size
            hashMp[item.id] = item.toDomainDRestaurant(rating)
        }
        menus.forEachIndexed{ _, item ->
            if(hashMp.containsKey(item.restaurantId)){
                val obj = hashMp[item.restaurantId]
                var newMenu = ""
                item.categories.forEach {
                    var tempItem = ""
                    it.menuItems.forEach{ mItem ->
                        tempItem += mItem.name
                    }
                    newMenu += " $it.name $tempItem"
                }
                obj?.menuItems += " $newMenu"
            }

        }

        hashMp.forEach { (_, dRestaurant) ->
            list.add(dRestaurant)
        }
        return list
    }

}