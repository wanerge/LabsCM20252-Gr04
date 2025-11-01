package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PersonalDataViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalDataUiState())
    val uiState: StateFlow<PersonalDataUiState> = _uiState.asStateFlow()

    // Datos campos1
    fun updateFirstName(firstName: String) {
        _uiState.update { it.copy(firstName = firstName) }
        validatePersonalForm()
    }

    fun updateLastName(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
        validatePersonalForm()
    }

    fun updateGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun updateBirthDate(birthDate: String) {
        _uiState.update { it.copy(birthDate = birthDate) }
        validatePersonalForm()
    }

    fun updateEducationLevel(educationLevel: String) {
        _uiState.update { it.copy(educationLevel = educationLevel) }
    }

    // Datos campos2
    fun updatePhone(phone: String) {
        _uiState.update { it.copy(phone = phone) }
        validateContactForm()
    }

    fun updateAddress(address: String) {
        _uiState.update { it.copy(address = address) }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
        validateContactForm()
    }

    fun updateCountry(country: String) {
        _uiState.update { it.copy(country = country) }
        validateContactForm()
    }

    fun updateCity(city: String) {
        _uiState.update { it.copy(city = city) }
    }

    // Validar
    private fun validatePersonalForm() {
        val s = _uiState.value
        val ok = s.firstName.isNotBlank() && s.lastName.isNotBlank() && s.birthDate.isNotBlank()
        _uiState.update { it.copy(isButtonEnabled = ok) }
    }

    private fun validateContactForm() {
        val s = _uiState.value
        val ok = s.phone.isNotBlank() && s.email.isNotBlank() && s.country.isNotBlank()
        _uiState.update { it.copy(isContactButtonEnabled = ok) }
    }

    // Imprimir en catsito
    fun printSummaryToLog() {
        val s = _uiState.value
        Log.d("Lab1", """
            Información personal:
            ${s.firstName} ${s.lastName}
            Sexo: ${s.gender}
            Nació el: ${s.birthDate}
            Escolaridad: ${s.educationLevel}

            Información de contacto:
            Teléfono: ${s.phone}
            Dirección: ${s.address}
            Email: ${s.email}
            País: ${s.country}
            Ciudad: ${s.city}
        """.trimIndent())
    }
}