package com.satyasetu.data.service

import com.satyasetu.data.model.PropertyRecord
import com.satyasetu.data.model.PropertyStatus
import kotlinx.coroutines.delay

class PropertyVerificationService {

    // यह फंक्शन भविष्य में असली API से जुड़ेगा। अभी के लिए यह डमी डेटा देता है।
    suspend fun verifyPropertyStatus(khasraNo: String, state: String): PropertyRecord {
        // असली ऐप में यहाँ इंटरनेट से डेटा लाने का कोड (Retrofit/OkHttp) होगा।
        // अभी हम सिर्फ दिखावे (simulation) के लिए 2 सेकंड का डिले दे रहे हैं।
        delay(2000)
        
        return if (state == "उत्तर प्रदेश" && khasraNo == "KH-102") {
            PropertyRecord(
                khasraNumber = khasraNo,
                khataNumber = "KT-55",
                ownerName = "अमित शर्मा",
                state = state,
                district = "लखनऊ",
                areaInSqFt = 2400.0,
                reraRegistrationId = null,
                hasCourtStayOrder = true,
                isBankMortgaged = true,
                verificationStatus = PropertyStatus.UNDER_DISPUTE
            )
        } else {
            PropertyRecord(
                khasraNumber = khasraNo,
                khataNumber = "KT-99",
                ownerName = "सुरेश कुमार",
                state = state,
                district = "जयपुर",
                areaInSqFt = 1200.0,
                reraRegistrationId = "RERA-RJ-2024-001",
                hasCourtStayOrder = false,
                isBankMortgaged = false,
                verificationStatus = PropertyStatus.VERIFIED
            )
        }
    }
}
