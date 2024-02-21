package edu.uw.ischool.jho12.quizdroid

import android.app.Application
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class QuizApp : Application() {
    lateinit var topicRepository: TopicRepository

    override fun onCreate() {
        super.onCreate()
        instance = this
        topicRepository = JsonTopicRepository(this)
        downloadQuestionsJson()
    }

    private fun downloadQuestionsJson() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkUtils.isNetworkAvailable(this@QuizApp)) {
                    val prefs = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
                    val url = prefs.getString("questions_url", "https://example.com/questions.json")!!
                    val input = URL(url).openStream()
                    val outputFile = File(filesDir, "questions.json.temp")
                    val outputStream = FileOutputStream(outputFile)

                    input.copyTo(outputStream)
                    input.close()
                    outputStream.close()

                    val finalFile = File(filesDir, "questions.json")
                    if (outputFile.renameTo(finalFile)) {
                        showToast("Questions downloaded successfully")
                    } else {
                        showToast("Failed to update questions file")
                    }
                } else {
                    if (NetworkUtils.isAirplaneModeOn(this@QuizApp)) {
                        showToast("You're in Airplane Mode. Please turn it off to download questions.")
                        // Direct user to settings, this needs to be done in an Activity context
                    } else {
                        showToast("No network connection available.")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Failed to download questions: ${e.message}")
            }
        }
    }

    private fun showToast(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@QuizApp, message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        lateinit var instance: QuizApp
            private set
    }
}
