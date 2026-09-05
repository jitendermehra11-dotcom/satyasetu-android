package com.satyasetu.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyasetu.data.model.PropertyRecord
import com.satyasetu.ui.viewmodel.VerificationViewModel

@Composable
fun PropertyVerificationScreen(viewModel: VerificationViewModel) {
    var khasraInput by remember { mutableStateOf("") }
    var stateInput by remember { mutableStateOf("उत्तर प्रदेश") }
    var searchResult by remember { mutableStateOf<PropertyRecord?>(null) }

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

        Button(
            onClick = {
                // यहाँ हम सर्विस या व्यूमॉडल से डेटा फेच करेंगे
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("सत्यापन जाँच करें")
        }

        Spacer(modifier = Modifier.height(24.dp))

        searchResult?.let { record ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "स्वामी: ${record.ownerName}", fontWeight = FontWeight.Bold)
                    Text(text = "जिला: ${record.district}, ${record.state}")
                    Text(text = "क्षेत्रफल: ${record.areaInSqFt} वर्ग फीट")
                    Text(text = "स्टेटस: ${record.verificationStatus}")
                }
            }
        }
    }
}
