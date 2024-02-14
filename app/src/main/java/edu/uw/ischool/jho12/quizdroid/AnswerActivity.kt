package edu.uw.ischool.jho12.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.uw.ischool.jho12.quizdroid.ui.theme.QuizdroidTheme

class AnswerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedAnswer = intent.getStringExtra("selectedAnswer") ?: ""
        val topicName = intent.getStringExtra("topicName") ?: ""
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        var correctAnswers = intent.getIntExtra("correctAnswers", 0)

        val topic = (application as QuizApp).topicRepository.getTopics().find { it.name == topicName }
        val question = topic?.questions?.getOrNull(currentQuestionIndex)
        val correctAnswer = question?.correctAnswer ?: ""
        val totalQuestions = topic?.questions?.size ?: 0

        if (selectedAnswer == correctAnswer) {
            correctAnswers++
        }

        setContent {
            QuizdroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnswerScreen(
                        selectedAnswer = selectedAnswer,
                        correctAnswer = correctAnswer,
                        currentQuestionIndex = currentQuestionIndex,
                        totalQuestions = totalQuestions,
                        correctAnswers = correctAnswers,
                        onNext = {
                            val intent = if (currentQuestionIndex + 1 < totalQuestions) {
                                Intent(this@AnswerActivity, QuestionActivity::class.java).apply {
                                    putExtra("topicName", topicName)
                                    putExtra("currentQuestionIndex", currentQuestionIndex + 1)
                                    putExtra("correctAnswers", correctAnswers)
                                }
                            } else {
                                Intent(this@AnswerActivity, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerScreen(
    selectedAnswer: String,
    correctAnswer: String,
    currentQuestionIndex: Int,
    totalQuestions: Int,
    correctAnswers: Int,
    onNext: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Your Answer: $selectedAnswer", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Correct Answer: $correctAnswer", style = MaterialTheme.typography.bodyLarge)
        Text(text = "You have $correctAnswers out of $totalQuestions correct", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onNext, modifier = Modifier.padding(top = 16.dp)) {
            Text(if (currentQuestionIndex + 1 < totalQuestions) "Next" else "Finish")
        }
    }
}
