package com.kunal.smartmathalarm

import android.app.Activity
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat

class AlarmRingingActivity : Activity() {
    private var ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val storage = AlarmStorage(this)
        val problem = MathProblemGenerator.generate(storage.difficulty)
        ringtone = RingtoneManager.getRingtone(
            this,
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ).also { it.play() }

        setContent {
            var answer by remember { mutableStateOf("") }
            var attempts by remember { mutableIntStateOf(0) }
            var message by remember { mutableStateOf("Solve the equation to stop the alarm.") }

            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Good morning.", style = MaterialTheme.typography.headlineMedium)
                    Text(
                        problem.expression,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                    OutlinedTextField(
                        value = answer,
                        onValueChange = { answer = it.filter(Char::isDigit) },
                        label = { Text("Answer for x") }
                    )
                    Button(
                        onClick = {
                            attempts++
                            if (answer.toIntOrNull() == problem.answer) {
                                stopAlarm()
                            } else {
                                message = "Wrong answer. Try again."
                                answer = ""
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Dismiss alarm")
                    }
                    Text(message, modifier = Modifier.padding(top = 16.dp))
                    Text("Attempts: $attempts")
                }
            }
        }
    }

    private fun stopAlarm() {
        ringtone?.stop()
        NotificationManagerCompat.from(this).cancel(AlarmReceiver.NOTIFICATION_ID)
        AlarmStorage(this).let { storage ->
            if (storage.enabled) AlarmScheduler.schedule(this, storage.hour, storage.minute)
        }
        finish()
    }

    override fun onDestroy() {
        ringtone?.stop()
        super.onDestroy()
    }
}
