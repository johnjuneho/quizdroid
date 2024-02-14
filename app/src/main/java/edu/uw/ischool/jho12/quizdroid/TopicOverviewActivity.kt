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

class TopicOverviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val topicName = intent.getStringExtra("topicName") ?: "Unknown"

        val topic = (application as QuizApp).topicRepository.getTopics().find { it.name == topicName }

        setContent {
            QuizdroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    topic?.let {
                        TopicOverview(
                            topicName = it.name,
                            topicDescription = it.longDescription,
                            totalQuestions = it.questions.size,
                            onBeginClicked = {
                                val intent = Intent(this@TopicOverviewActivity, QuestionActivity::class.java).apply {
                                    putExtra("topicName", it.name)
                                }
                                startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopicOverview(topicName: String, topicDescription: String, totalQuestions: Int, onBeginClicked: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = topicName, style = MaterialTheme.typography.headlineMedium)
        Text(text = topicDescription, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
        Text(text = "Total Questions: $totalQuestions", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 8.dp))
        Button(onClick = onBeginClicked, modifier = Modifier.padding(top = 16.dp)) {
            Text("Begin")
        }
    }
}
