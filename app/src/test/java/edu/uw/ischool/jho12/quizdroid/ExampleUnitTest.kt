package edu.uw.ischool.jho12.quizdroid

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class InMemoryTopicRepositoryTest {

    private lateinit var repository: InMemoryTopicRepository

    @Before
    fun setUp() {
        // Initialize your repository before each test
        repository = InMemoryTopicRepository()
    }

    @Test
    fun `getTopics returns the correct number of topics`() {
        val topics = repository.getTopics()
        assertEquals(4, topics.size)
    }

    @Test
    fun `getTopics returns topics with correct names`() {
        val topics = repository.getTopics()
        val topicNames = topics.map { it.name }
        assertEquals(listOf("Math", "Physics", "Marvel Super Heroes", "History"), topicNames)
    }

    @Test
    fun `each topic contains the correct number of questions`() {
        val topics = repository.getTopics()
        topics.forEach { topic ->
            when (topic.name) {
                "Math" -> assertEquals(4, topic.questions.size)
                "Physics" -> assertEquals(4, topic.questions.size)
                "Marvel Super Heroes" -> assertEquals(4, topic.questions.size)
                "History" -> assertEquals(4, topic.questions.size)
                else -> throw Exception("Unexpected topic found")
            }
        }
    }

    @Test
    fun `questions in a specific topic contain the correct answers`() {
        val topics = repository.getTopics()
        val mathQuestions = topics.find { it.name == "Math" }?.questions ?: throw Exception("Math topic not found")
        assertEquals("4", mathQuestions[0].correctAnswer)
        assertEquals("4", mathQuestions[1].correctAnswer)
        assertEquals("100", mathQuestions[2].correctAnswer)
        assertEquals("2x", mathQuestions[3].correctAnswer)
    }
}
