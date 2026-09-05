package com.satyasetu.data.model

// 1. ज़मीन व प्रॉपर्टी सत्यापन मॉडल
data class PropertyRecord(
    val khasraNumber: String,
    val khataNumber: String,
    val ownerName: String,
    val state: String,
    val district: String,
    val areaInSqFt: Double,
    val reraRegistrationId: String? = null,
    val hasCourtStayOrder: Boolean = false,
    val isBankMortgaged: Boolean = false,
    val verificationStatus: PropertyStatus = PropertyStatus.VERIFIED
)

enum class PropertyStatus {
    VERIFIED,
    UNDER_DISPUTE,
    PENDING_CLEARANCE
}

// 2. सिटिजन व यूटिलिटी सर्विसेज मॉडल
data class CitizenServiceItem(
    val id: String,
    val title: String,
    val category: ServiceCategory,
    val portalUrl: String,
    val description: String
)

enum class ServiceCategory {
    PROPERTY_LEGAL,
    VEHICLE_CHALLAN_HSRP,
    CITIZEN_ID_SIM,
    FINANCIAL_CIBIL_TDS
}

// 3. आपदा प्रबंधन (Offline Mesh Emergency Beacon)
data class EmergencyBeacon(
    val senderId: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long = System.currentTimeMillis(),
    val batteryPercentage: Int,
    val distressMessage: String = "EMERGENCY SOS: NEED RESCUE"
)
