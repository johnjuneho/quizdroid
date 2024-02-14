package edu.uw.ischool.jho12.quizdroid

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)
