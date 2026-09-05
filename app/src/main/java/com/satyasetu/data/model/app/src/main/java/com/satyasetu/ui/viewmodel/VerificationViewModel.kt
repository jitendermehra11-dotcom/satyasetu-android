package com.satyasetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.satyasetu.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VerificationViewModel : ViewModel() {

    private val _propertyListings = MutableStateFlow<List<PropertyRecord>>(emptyList())
    val propertyListings: StateFlow<List<PropertyRecord>> = _propertyListings

    private val _citizenServices = MutableStateFlow<List<CitizenServiceItem>>(emptyList())
    val citizenServices: StateFlow<List<CitizenServiceItem>> = _citizenServices

    private val _sosBeacon = MutableStateFlow<EmergencyBeacon?>(null)
    val sosBeacon: StateFlow<EmergencyBeacon?> = _sosBeacon

    init {
        loadDefaultCitizenServices()
        loadSamplePropertyRecords()
    }

    private fun loadDefaultCitizenServices() {
        _citizenServices.value = listOf(
            CitizenServiceItem("1", "भूलेख / घरौनी व खसरा जांच", ServiceCategory.PROPERTY_LEGAL, "https://upbhulekh.gov.in", "अपनी ज़मीन/प्लॉट का ऑनलाइन रिकॉर्ड देखें"),
            CitizenServiceItem("2", "HSRP नंबर प्लेट ऑनलाइन बुकिंग", ServiceCategory.VEHICLE_CHALLAN_HSRP, "https://bookmyhsrp.com", "हाई सिक्योरिटी नंबर प्लेट अप्लाई करें"),
            CitizenServiceItem("3", "ई-चालान स्टेटस व भुगतान", ServiceCategory.VEHICLE_CHALLAN_HSRP, "https://echallan.parivahan.gov.in", "गाड़ी का पेंडिंग चालान चेक करें"),
            CitizenServiceItem("4", "आभा / वोटर कार्ड पोर्टल", ServiceCategory.CITIZEN_ID_SIM, "https://voters.eci.gov.in", "नया वोटर कार्ड व संशोधन सेवा"),
            CitizenServiceItem("5", "CIBIL क्रेडिट रिपोर्ट (मुफ़्त)", ServiceCategory.FINANCIAL_CIBIL_TDS, "https://cibil.com", "अपना सिविल स्कोर सुरक्षित जांचें")
        )
    }

    private fun loadSamplePropertyRecords() {
        _propertyListings.value = listOf(
            PropertyRecord("102/4", "KH-883", "सुरेश कुमार", "राजस्थान", "जयपुर", 1200.0, "RERA-RJ-2024-001", false, false, PropertyStatus.VERIFIED),
            PropertyRecord("45/1", "KH-102", "अमित शर्मा", "उत्तर प्रदेश", "लखनऊ", 2400.0, null, true, true, PropertyStatus.UNDER_DISPUTE)
        )
    }

    fun triggerEmergencySOS(lat: Double, lng: Double, battery: Int) {
        _sosBeacon.value = EmergencyBeacon(
            senderId = "USER_NODE_01",
            latitude = lat,
            longitude = lng,
            batteryPercentage = battery,
            distressMessage = "EMERGENCY SOS: Mesh Signal Active"
        )
    }
}
