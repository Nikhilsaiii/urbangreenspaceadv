package com.example.urbangreenspace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

data class GreenSpace(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Float
)

class MainViewModel : ViewModel() {

    private val _greenSpaces = MutableLiveData<List<GreenSpace>>()
    val greenSpaces: LiveData<List<GreenSpace>> = _greenSpaces

    init {
        loadGreenSpaces()
    }

    private fun loadGreenSpaces() {
        // For demo, load from local JSON file in assets
        viewModelScope.launch {
            val jsonString = loadJSONFromAsset("greenspaces.json")
            jsonString?.let {
                val list = parseGreenSpaces(it)
                _greenSpaces.postValue(list)
            }
        }
    }

    private fun loadJSONFromAsset(filename: String): String? {
        return try {
            val inputStream = getApplication<Application>().assets.open(filename)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            bufferedReader.forEachLine { sb.append(it) }
            sb.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    private fun parseGreenSpaces(jsonString: String): List<GreenSpace> {
        val list = mutableListOf<GreenSpace>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val greenSpace = GreenSpace(
                id = obj.getInt("id"),
                name = obj.getString("name"),
                description = obj.getString("description"),
                latitude = obj.getDouble("latitude"),
                longitude = obj.getDouble("longitude"),
                rating = obj.getDouble("rating").toFloat()
            )
            list.add(greenSpace)
        }
        return list
    }
}
