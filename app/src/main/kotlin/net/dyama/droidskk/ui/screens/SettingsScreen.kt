package net.dyama.droidskk.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
  Scaffold(
    topBar = {
      TopAppBar({
        Text("DroidSKK")
      })
    }
  ) { contentPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
      val text = remember { mutableStateOf("") }
      OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier
          .fillMaxWidth()
          .height(100.dp),
        label = { Text("試し撃ち") },
      )
    }
  }
}
