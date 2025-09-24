package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

data class PersonalDataUiState(
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val educationLevel: String = "",
    val isButtonEnabled: Boolean = false
)
