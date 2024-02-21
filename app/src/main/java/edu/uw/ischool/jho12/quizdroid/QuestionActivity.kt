package edu.uw.ischool.jho12.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.uw.ischool.jho12.quizdroid.ui.theme.QuizdroidTheme
import androidx.compose.foundation.layout.Row
import android.widget.Toast

class QuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val topicName = intent.getStringExtra("topicName") ?: ""
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)

        val topicRepository = (application as QuizApp).topicRepository
        val topic = topicRepository.getTopics().find { it.title == topicName }
        val question = topic?.questions?.getOrNull(currentQuestionIndex)

        if (question != null) {
            setContent {
                QuizdroidTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        question?.let {
                            QuestionPage(question = it.text, answers = it.answers, onSubmit = { selectedAnswer ->
                                val correctAnswerText = it.answers[it.answer]
                                val intent = Intent(this, AnswerActivity::class.java).apply {
                                    putExtra("selectedAnswer", selectedAnswer)
                                    putExtra("correctAnswer", correctAnswerText)
                                    putExtra("currentQuestionIndex", currentQuestionIndex)
                                    putExtra("totalQuestions", topic?.questions?.size ?: 0)
                                    putExtra("topicName", topicName)
                                }
                                startActivity(intent)
                            })
                        }
                    }
                }
            }

        } else {
            Toast.makeText(this, "Error loading question. Please try again.", Toast.LENGTH_LONG).show()
            finish()
        }
    }


override fun onBackPressed() {
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        if (currentQuestionIndex == 0) {
            Intent(this, MainActivity::class.java).also { mainActivityIntent ->
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(mainActivityIntent)
                finish()
            }
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun QuestionPage(
    question: String,
    answers: List<String>,
    onSubmit: (String) -> Unit
) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = question, style = MaterialTheme.typography.headlineMedium)
        answers.forEach { answer ->
            Row(modifier = Modifier.padding(8.dp)) {
                RadioButton(
                    selected = selectedAnswer == answer,
                    onClick = { selectedAnswer = answer },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Text(text = answer, modifier = Modifier.padding(start = 8.dp))
            }
        }
        if (selectedAnswer != null) {
            Button(
                onClick = { onSubmit(selectedAnswer!!) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}



