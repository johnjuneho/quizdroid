package edu.uw.ischool.jho12.quizdroid

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

class JsonTopicRepository(private val context: Context) : TopicRepository {
    private var topics: List<Topic> = listOf()

    init {
        topics = loadTopicsFromJson()
    }

    private fun loadTopicsFromJson(): List<Topic> {
        val jsonFileString = getJsonDataFromAsset(context, "questions.json")
        val gson = Gson()
        return gson.fromJson(jsonFileString, Array<Topic>::class.java).toList()
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun getTopics(): List<Topic> = topics
}
