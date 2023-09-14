package com.example.gymmate.alarmclockui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.*

class AlarmClockUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp { date -> setAlarm(date) }
            }
        }
    }

    private fun setAlarm(date: Date) {
        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && alarmManager.canScheduleExactAlarms()) {
            alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent), pendingIntent)
        } else if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) {
            alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent), pendingIntent)
        } else {
            Toast.makeText(this, "Please grant the permission to set exact alarms.", Toast.LENGTH_SHORT).show()
            val alarmIntent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(alarmIntent)
        }
    }
}

@Composable
fun MyApp(onSetAlarm: (Date) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Images Section - occupies 2/3 of the screen
        ImagePlaceholder()
        Spacer(modifier = Modifier.height(16.dp))
        ImagePlaceholder()

        Spacer(modifier = Modifier.height(16.dp))

        // 2x2 Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO: Handle button click*/ }) {
                Text("Button 1")
            }

            Button(onClick = { /*TODO: Handle button click*/ }) {
                Text("Button 2")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO: Handle button click*/ }) {
                Text("Button 3")
            }

            Button(onClick = { /*TODO: Handle button click*/ }) {
                Text("Button 4")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Centered "Set Alarm" Button
        Button(onClick = { onSetAlarm(Date()) }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Set Alarm")
        }
    }
}

@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.33f)  // This ensures each image occupies 1/3 of the available height
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Image Placeholder")  // Replace this with your actual image
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApplicationTheme {
        MyApp {}
    }
}