package com.kunal.smartmathalarm

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private lateinit var storage: AlarmStorage

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = AlarmStorage(this)
        requestNotificationPermission()

        setContent {
            AlarmHome(
                storage = storage,
                onEnable = { hour, minute -> enableAlarm(hour, minute) },
                onDisable = { disableAlarm() }
            )
        }
    }

    private fun enableAlarm(hour: Int, minute: Int) {
        storage.hour = hour
        storage.minute = minute
        storage.enabled = true
        storage.difficulty = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(AlarmManager::class.java)
            if (!alarmManager.canScheduleExactAlarms()) {
                startActivity(
                    Intent(
                        Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                        Uri.parse("package:$packageName")
                    )
                )
                return
            }
        }

        AlarmScheduler.schedule(this, hour, minute)
    }

    private fun disableAlarm() {
        storage.enabled = false
        AlarmScheduler.cancel(this)
    }

    private fun requestNotificationPermission() {
        if (
            Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

@Composable
private fun AlarmHome(
    storage: AlarmStorage,
    onEnable: (Int, Int) -> Unit,
    onDisable: () -> Unit
) {
    val timeState = rememberTimePickerState(storage.hour, storage.minute, true)

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Smart Math Alarm", style = MaterialTheme.typography.headlineLarge)
            Text(
                "Set an alarm you have to solve your way out of.",
                modifier = Modifier.padding(vertical = 12.dp)
            )
            TimePicker(state = timeState)
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Button(onClick = { onEnable(timeState.hour, timeState.minute) }) {
                    Text("Enable alarm")
                }
                Button(
                    onClick = onDisable,
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text("Disable")
                }
            }
            Text(
                if (storage.enabled) "Daily alarm enabled" else "No alarm scheduled",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
