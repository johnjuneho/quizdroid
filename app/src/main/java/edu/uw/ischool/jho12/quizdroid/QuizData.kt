package edu.uw.ischool.jho12.quizdroid

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)

val questions = listOf(
    Question(
        question = "What is 2 + 2?",
        answers = listOf("1", "2", "3", "4"),
        correctAnswer = "4"
    ),
    Question(
        question = "What is the capital of France?",
        answers = listOf("Madrid", "Berlin", "Paris", "Lisbon"),
        correctAnswer = "Paris"
    )
)
