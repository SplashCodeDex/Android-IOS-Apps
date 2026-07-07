package com.example.apptwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.core.sharedui.CoreHeader
import com.company.core.shareddata.CoreRepository

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            CoreHeader(title = "App Two Dashboard")
            Text(
              text = "Data from Core Engine: ${CoreRepository.getAppConfig()}",
              modifier = Modifier.padding(16.dp)
            )
          }
        }
    }
  }
}
