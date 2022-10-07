package com.pam.myrestaurants.hilt

import android.content.Context
import com.pam.myrestaurants.common.Constants
import com.pam.myrestaurants.data.remote.APIService
import com.pam.myrestaurants.data.repository.RestaurantSearchRepositoryImpl
import com.pam.myrestaurants.domain.repository.RestaurantSearchRepository
import com.pam.myrestaurants.utils.JsonHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HIltModules {

    @Singleton
    @Provides
    fun provideJsonHelper(@ApplicationContext appContext: Context): JsonHelper = JsonHelper(context = appContext)


    @Provides
    fun provideMealSearchRepository(jsonHelper: JsonHelper): RestaurantSearchRepository {
        return RestaurantSearchRepositoryImpl(jsonHelper )
    }

    @Provides
    @Singleton
    fun provideAPIService(): APIService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }

}