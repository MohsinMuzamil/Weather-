
package com.mohsin.weather

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var cityEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var weatherIcon: ImageView
    private lateinit var tempText: TextView
    private lateinit var conditionText: TextView
    private lateinit var extraText: TextView
    private lateinit var loader: ProgressBar

    private val apiKey = "c6e3492e07b24e08958111104252105"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityEditText = findViewById(R.id.cityEditText)
        searchButton = findViewById(R.id.searchButton)
        weatherIcon = findViewById(R.id.weatherIcon)
        tempText = findViewById(R.id.tempText)
        conditionText = findViewById(R.id.conditionText)
        extraText = findViewById(R.id.extraText)
        loader = findViewById(R.id.loader)

        searchButton.setOnClickListener {
            val city = cityEditText.text.toString()
            if (city.isNotEmpty()) {
                fetchWeather(city)
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchWeather(city: String) {
        loader.visibility = View.VISIBLE
        val url = "https://api.weatherapi.com/v1/current.json?key=$apiKey&q=$city&aqi=no"
        val request = Request.Builder().url(url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    loader.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                runOnUiThread {
                    loader.visibility = View.GONE
                    try {
                        val obj = JSONObject(json!!)
                        val current = obj.getJSONObject("current")
                        val condition = current.getJSONObject("condition")
                        val tempC = current.getDouble("temp_c")
                        val text = condition.getString("text")
                        val iconUrl = "https:" + condition.getString("icon")
                        val humidity = current.getInt("humidity")
                        val wind = current.getDouble("wind_kph")
                        tempText.text = "$tempC°C"
                        conditionText.text = text
                        extraText.text = "Humidity: $humidity%  |  Wind: $wind kph"
                        Glide.with(this@MainActivity).load(iconUrl).into(weatherIcon)
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, "Invalid city or API error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}