package com.satyasetu.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyasetu.data.model.PropertyStatus
import com.satyasetu.ui.viewmodel.VerificationViewModel

@Composable
fun PropertyVerificationScreen(viewModel: VerificationViewModel) {
    var khasraInput by remember { mutableStateOf("") }
    var stateInput by remember { mutableStateOf("उत्तर प्रदेश") }
    val searchResult by viewModel.selectedProperty.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "भूलेख व खसरा सत्यापन",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = khasraInput,
            onValueChange = { khasraInput = it },
            label = { Text("खसरा नंबर दर्ज करें (जैसे: KH-102)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = stateInput,
            onValueChange = { stateInput = it },
            label = { Text("राज्य (जैसे: उत्तर प्रदेश या राजस्थान)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (khasraInput.isNotBlank()) {
                    viewModel.verifyProperty(khasraInput, stateInput)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("सत्यापन जाँच करें")
        }

        Spacer(modifier = Modifier.height(24.dp))

        searchResult?.let { record ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "परिणाम विवरण:", fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "खसरा नंबर: ${record.khasraNumber}")
                    Text(text = "स्वामी: ${record.ownerName}")
                    Text(text = "जिला: ${record.district}, ${record.state}")
                    Text(text = "क्षेत्रफल: ${record.areaInSqFt} वर्ग फीट")
                    Text(
                        text = "स्टेटस: ${record.verificationStatus}",
                        fontWeight = FontWeight.Bold,
                        color = if (record.verificationStatus == PropertyStatus.VERIFIED) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                }
            }
        }
    }
}
