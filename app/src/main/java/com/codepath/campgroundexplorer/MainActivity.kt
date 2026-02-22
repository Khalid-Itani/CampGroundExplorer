package com.codepath.campgroundexplorer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    // Backing list for adapter
    private val campgrounds = mutableListOf<Campground>()

    // Build URL with API key from BuildConfig
    private val PARKS_API_KEY = BuildConfig.API_KEY

    // Add limit so responses are smaller and predictable
    private val CAMPGROUND_URL =
        "https://developer.nps.gov/api/v1/campgrounds?limit=50&api_key=$PARKS_API_KEY"

    private lateinit var campgroundsRecyclerView: RecyclerView
    private lateinit var campgroundAdapter: CampgroundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campgroundsRecyclerView = findViewById(R.id.campgroundsRecyclerView)

        campgroundAdapter = CampgroundAdapter(this, campgrounds)
        campgroundsRecyclerView.adapter = campgroundAdapter
        campgroundsRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchCampgrounds()
    }

    private fun fetchCampgrounds() {
        val client = AsyncHttpClient()
        client.get(CAMPGROUND_URL, object : JsonHttpResponseHandler() {

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch campgrounds: $statusCode")
                Log.e(TAG, "Response: $response", throwable)
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched campgrounds. Status=$statusCode")

                try {
                    // IMPORTANT: Use the org.json JSONObject string directly
                    val jsonString = json.jsonObject.toString()
                    Log.d(TAG, "Raw JSON length: ${jsonString.length}")

                    val parsedJson = createJson().decodeFromString(
                        CampgroundResponse.serializer(),
                        jsonString
                    )

                    campgrounds.clear()

                    val list = parsedJson.data ?: emptyList()
                    Log.d(TAG, "Parsed campgrounds count: ${list.size}")

                    campgrounds.addAll(list)
                    campgroundAdapter.notifyDataSetChanged()

                } catch (e: Exception) {
                    Log.e(TAG, "Exception parsing campgrounds", e)
                }
            }
        })
    }

    private fun createJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}