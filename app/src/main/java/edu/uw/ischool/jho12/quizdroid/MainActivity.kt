package edu.uw.ischool.jho12.quizdroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import edu.uw.ischool.jho12.quizdroid.ui.theme.QuizdroidTheme
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizdroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizTopicsList()
                }
            }
        }
    }
}

@Composable
fun QuizTopicsList(modifier: Modifier = Modifier) {
    val topics = listOf(
        Topic("Math", "Description for Math", 2),
        Topic("Physics", "Description for Physics", 2),
        Topic("Marvel Super Heroes", "Description for Marvel Super Heroes", 2),
        Topic("History", "Description for History", 2)
    )

    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Select a Topic:", style = MaterialTheme.typography.headlineMedium)
        topics.forEach { topic ->
            Button(
                onClick = {
                    val intent = Intent(context, TopicOverviewActivity::class.java).apply {
                        putExtra("topicName", topic.name)
                        putExtra("topicDescription", topic.description)
                        putExtra("totalQuestions", topic.totalQuestions)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = topic.name, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizTopicsListPreview() {
    QuizdroidTheme {
        QuizTopicsList()
    }
}
