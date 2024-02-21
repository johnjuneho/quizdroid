package edu.uw.ischool.jho12.quizdroid
import android.app.Application
import android.util.Log

class QuizApp : Application() {
    lateinit var topicRepository: TopicRepository

    override fun onCreate() {
        super.onCreate()
        instance = this
        topicRepository = JsonTopicRepository(this)
    }

    companion object {
        lateinit var instance: QuizApp
            private set
    }
}


