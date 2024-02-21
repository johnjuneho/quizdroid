package edu.uw.ischool.jho12.quizdroid

data class Question(
    val text: String,
    val answers: List<String>,
    val answer: Int
)
