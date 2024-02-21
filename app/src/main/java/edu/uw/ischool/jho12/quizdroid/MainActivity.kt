package edu.uw.ischool.jho12.quizdroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizdroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { TopBar() }
                    ) {
                        QuizTopicsList()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar() {
        val context = LocalContext.current
        CenterAlignedTopAppBar(
            title = { Text("Quiz Topics") },
            actions = {
                IconButton(onClick = { context.startActivity(Intent(context, PreferencesActivity::class.java)) }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Preferences")
                }
            }
        )
    }

    @Composable
    fun QuizTopicsList() {
        val topics = QuizApp.instance.topicRepository.getTopics()
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Select a Topic:",
                style = MaterialTheme.typography.headlineMedium
            )
            topics.forEach { topic ->
                TopicCard(topic = topic) {
                    val intent = Intent(context, TopicOverviewActivity::class.java).apply {
                        putExtra("topicName", topic.title)
                        putExtra("topicDescription", topic.desc)
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
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = topic.desc,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }

    @Composable
    fun QuizdroidTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            content = content
        )
    }
}
