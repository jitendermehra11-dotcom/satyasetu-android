package com.satyasetu.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyasetu.ui.viewmodel.VerificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencySosScreen(viewModel: VerificationViewModel, onBack: () -> Unit) {
    val sosBeacon by viewModel.sosBeacon.collectAsState()
    var isTriggered by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("आपातकालीन SOS (Emergency Mesh)", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFC62828),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFEBEE))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "⚠️ ऑफ़लाइन इमरजेंसी सिग्नल",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC62828),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "बिना इंटरनेट के ब्लूटूथ/मेश नेटवर्क के जरिए आसपास के सत्यसेतु यूजर्स तक डिस्ट्रेस सिग्नल भेजने के लिए नीचे दिए गए बटन को दबाएं।",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    isTriggered = true
                    viewModel.triggerEmergencySOS(26.9124, 75.7873, 85)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("SOS सिग्नल ब्रॉडकास्ट करें", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (isTriggered) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "ब्रॉडकास्ट स्टेटस:", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "नोड आईडी: ${sosBeacon?.senderId ?: "USER_NODE_01"}")
                        Text(text = "लोकेशन: ${sosBeacon?.latitude}, ${sosBeacon?.longitude}")
                        Text(text = "बैटरी: ${sosBeacon?.batteryPercentage}%")
                        Text(text = "संदेश: ${sosBeacon?.distressMessage}")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "status: Mesh Beacon Active & Broadcasting", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("होम पर वापस जाएं")
            }
        }
    }
}
