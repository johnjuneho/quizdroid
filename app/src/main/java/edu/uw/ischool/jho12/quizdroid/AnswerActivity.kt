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
        val correctAnswer = intent.getStringExtra("correctAnswer") ?: ""
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0) + if (selectedAnswer == correctAnswer) 1 else 0

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
                            val intent = if (currentQuestionIndex < totalQuestions - 1) {
                                Intent(this@AnswerActivity, QuestionActivity::class.java).apply {
                                    putExtra("currentQuestionIndex", currentQuestionIndex + 1)
                                    putExtra("totalQuestions", totalQuestions)
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
            Text(if (currentQuestionIndex < totalQuestions - 1) "Next" else "Finish")
        }
    }
}
