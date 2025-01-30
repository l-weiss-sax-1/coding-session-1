package nl.saxion.luuk.aoelocallookup.infrastructure

import android.util.Log
import kotlinx.serialization.json.Json
import nl.saxion.luuk.aoelocallookup.infrastructure.DAO.Age
import nl.saxion.luuk.aoelocallookup.infrastructure.DAO.Civilization
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object Api{
    private val client = OkHttpClient()

    private var civs: List<Civilization>? = null

    // Generic post function that works with any type T and parses the response into that type
    private inline fun <reified T> post(url: String, crossinline callback: (T?) -> Unit, crossinline parser: (String) -> T) {
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
        if (civs.isNullOrEmpty()) {
            post("https://aoe2-data-api.herokuapp.com/civs?includeUnits=true&includeTechs=true&includeBuildings=true", callback) { responseBody ->
                parseCivData(responseBody)
            }
        }
        else {
            return callback(civs)
        }
    }

    private fun parseCivData(jsonString: String): List<Civilization> {
        val result: List<Civilization> = Json { ignoreUnknownKeys = true }.decodeFromString(jsonString)
        this.civs = result
        return result
    }

    private fun parseAgesData(jsonString: String): List<Age> {
        return Json {ignoreUnknownKeys = true}.decodeFromString(jsonString)
    }

    fun fetchAges(callback: (List<Age>?) -> Unit) {
        post("https://aoe2-data-api.herokuapp.com/ages", callback) {
            responseBody -> parseAgesData(responseBody)
        }
    }

    private fun parseAgeData(jsonString: String): Age {
        return Json {ignoreUnknownKeys = true}.decodeFromString(jsonString)
    }

    fun fetchAgeById(id: Int, callback: (Age?) -> Unit) {
        post("https://aoe2-data-api.herokuapp.com/ages/${id}", callback) {
            responseBody -> parseAgeData(responseBody)
        }
    }
}
