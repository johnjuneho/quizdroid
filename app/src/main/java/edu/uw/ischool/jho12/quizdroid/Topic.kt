package edu.uw.ischool.jho12.quizdroid

data class Topic(
    val title: String,
    val desc: String,
    val questions: List<Question>
)
