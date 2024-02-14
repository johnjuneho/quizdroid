package edu.uw.ischool.jho12.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource

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
fun QuizTopicsList() {
    val topics = QuizApp.instance.topicRepository.getTopics()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Select a Topic:",
            style = MaterialTheme.typography.headlineMedium
        )
        topics.forEach { topic ->
            TopicCard(topic = topic) {
                val intent = Intent(context, TopicOverviewActivity::class.java).apply {
                    putExtra("topicName", topic.name)
                    putExtra("topicDescription", topic.shortDescription)
                    putExtra("totalQuestions", topic.questions.size)
                }
                context.startActivity(intent)
            }
        }
    }
}
@Composable
fun TopicCard(topic: Topic, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = topic.iconResId),
                contentDescription = "${topic.name} icon",
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(
                    text = topic.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = topic.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
    Divider()
}

@Composable
fun QuizdroidTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}
