package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

data class PersonalDataUiState(
    // Pantalla 1: Datos personales
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val educationLevel: String = "",
    val isButtonEnabled: Boolean = false,

    // Pantalla 2: Datos de contacto
    val phone: String = "",
    val address: String = "",
    val email: String = "",
    val country: String = "",
    val city: String = "",
    val isContactButtonEnabled: Boolean = false
)
