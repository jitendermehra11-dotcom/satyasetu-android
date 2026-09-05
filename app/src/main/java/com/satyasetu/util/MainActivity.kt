package com.satyasetu.util

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyasetu.data.model.*
import com.satyasetu.ui.screen.PropertyVerificationScreen
import com.satyasetu.ui.screen.UtilityPortalScreen
import com.satyasetu.ui.viewmodel.VerificationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = VerificationViewModel()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("HOME") }
                    var activeServiceTitle by remember { mutableStateOf("") }
                    var activeServiceUrl by remember { mutableStateOf("") }

                    val services by viewModel.citizenServices.collectAsState()

                    when (currentScreen) {
                        "PROPERTY_SCREEN" -> {
                            PropertyVerificationScreen(viewModel)
                        }
                        "UTILITY_SCREEN" -> {
                            UtilityPortalScreen(
                                title = activeServiceTitle,
                                url = activeServiceUrl,
                                onBack = { currentScreen = "HOME" }
                            )
                        }
                        else -> {
                            SatyaSetuHomeScreen(
                                services = services,
                                onServiceClick = { service ->
                                    if (service.id == "1") {
                                        currentScreen = "PROPERTY_SCREEN"
                                    } else {
                                        activeServiceTitle = service.title
                                        activeServiceUrl = service.targetUrl
                                        currentScreen = "UTILITY_SCREEN"
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SatyaSetuHomeScreen(
    services: List<CitizenServiceItem>,
    onServiceClick: (CitizenServiceItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("सत्यसेतु (SatyaSetu)", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D47A1), 
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                text = "नागरिक व कानूनी सत्यापन पोर्टल",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                items(services) { service ->
                    ServiceCard(service) {
                        onServiceClick(service)
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceCard(service: CitizenServiceItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = service.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = service.description, fontSize = 13.sp, color = Color.Gray)
        }
    }
}
