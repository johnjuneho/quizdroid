package edu.uw.ischool.jho12.quizdroid

data class Topic(
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val questions: List<Question>,
    val iconResId: Int
)
