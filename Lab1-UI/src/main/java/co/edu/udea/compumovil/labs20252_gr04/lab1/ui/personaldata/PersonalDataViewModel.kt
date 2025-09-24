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

    // Funciones para actualizar el estado de los campos
    fun updateFirstName(firstName: String) {
        _uiState.update { it.copy(firstName = firstName) }
        validateForm()
    }

    fun updateLastName(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
        validateForm()
    }

    fun updateGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun updateBirthDate(birthDate: String) {
        _uiState.update { it.copy(birthDate = birthDate) }
        validateForm()
    }

    fun updateEducationLevel(educationLevel: String) {
        _uiState.update { it.copy(educationLevel = educationLevel) }
    }

    // Función para validar los campos obligatorios
    private fun validateForm() {
        val currentState = _uiState.value
        val isFormValid = currentState.firstName.isNotBlank() &&
                currentState.lastName.isNotBlank() &&
                currentState.birthDate.isNotBlank()
        _uiState.update { it.copy(isButtonEnabled = isFormValid) }
    }

}