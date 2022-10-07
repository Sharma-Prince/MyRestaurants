package com.pam.myrestaurants.utils

import android.content.Context
import java.io.IOException

/***
 *  Read the Json Data
 */
class JsonHelper(private val context: Context) {

    fun readJsonAssetsFile(jsonFile : String): String {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open(jsonFile)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {

        }

        return jsonString
    }
}