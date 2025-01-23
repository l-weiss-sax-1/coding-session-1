package nl.saxion.luuk.aoelocallookup.infrastructure

import android.util.Log
import kotlinx.serialization.json.Json
import nl.saxion.luuk.aoelocallookup.infrastructure.DAO.Civilization
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class api {
    val client = OkHttpClient()
    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    // Generic post function that works with any type T and parses the response into that type
    inline fun <reified T> post(url: String, crossinline callback: (T?) -> Unit, crossinline parser: (String) -> T) {
        val request = Request.Builder()
            .url(url)
            .addHeader("accept", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API", "Error occurred: ${e.message}")
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        try {
                            // Use the provided parser function to parse the response into the desired type T
                            val result = parser(responseBody)
                            Log.d("API", "Fetch success")
                            callback(result)
                        } catch (e: Exception) {
                            Log.e("API", "Error during parsing: ${e.message}")
                            callback(null)
                        }
                    } else {
                        Log.e("API", "Error: Response body is null")
                        callback(null)
                    }
                } else {
                    Log.e("API", "Request not successful: ${response.code}")
                    callback(null)
                }
            }
        })
    }

    // Example of how to call the post function with a specific type and a parser function
    fun fetchCivilizations(callback: (List<Civilization>?) -> Unit) {
        post("https://aoe2-data-api.herokuapp.com/civs?includeUnits=true&includeTechs=true&includeBuildings=true", callback) { responseBody ->
            parseCivData(responseBody)
        }
    }

    private fun parseCivData(jsonString: String): List<Civilization> {
        return Json { ignoreUnknownKeys = true }.decodeFromString(jsonString)
    }
}
