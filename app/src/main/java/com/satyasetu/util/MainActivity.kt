package com.satyasetu.util

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyasetu.ui.screens.UtilityPortalScreen

data class CitizenServiceItem(
    val title: String,
    val url: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppNavigation()
                }
            }
        }
    }
}

@Composable
fun MainAppNavigation() {
    var selectedService by remember { mutableStateOf<CitizenServiceItem?>(null) }

    if (selectedService == null) {
        SatyaSetuHomeScreen(
            onServiceClick = { service ->
                selectedService = service
            }
        )
    } else {
        UtilityPortalScreen(
            title = selectedService!!.title,
            url = selectedService!!.url,
            onBack = {
                selectedService = null
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SatyaSetuHomeScreen(onServiceClick: (CitizenServiceItem) -> Unit) {
    val services = listOf(
        CitizenServiceItem("भूलेख (Bhulekh)", "https://upbhulekh.gov.in/"),
        CitizenServiceItem("ई-चालान (e-Challan)", "https://echallan.parivahan.gov.in/"),
        CitizenServiceItem("HSRP प्लेट", "https://bookmyhsrp.com/"),
        CitizenServiceItem("वोटर ID सेवा", "https://voters.eci.gov.in/"),
        CitizenServiceItem("CIBIL स्कोर", "https://www.cibil.com/")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("सत्यसेतु - SatyaSetu", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "सरकारी एवं नागरिक सुविधाएं",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(services) { service ->
                    ServiceCard(service = service, onClick = { onServiceClick(service) })
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
            .height(110.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = service.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
