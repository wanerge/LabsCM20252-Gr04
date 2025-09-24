package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.composed
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

// ---------- APP PRINCIPAL ----------
@Composable
fun Lab1App() {
    val navController = rememberNavController()
    val viewModel: PersonalDataViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "personal"
    ) {
        composable("personal") {
            PersonalDataScreen(
                viewModel = viewModel,
                onNext = { navController.navigate("contact") }
            )
        }
        composable("contact") {
            ContactDataScreen(
                viewModel = viewModel,
                onNext = { navController.navigate("summary") }
            )
        }
        composable("summary") {
            SummaryScreen(
                viewModel = viewModel,
                onEdit = { navController.navigate("personal") }
            )
        }
    }
}

// Pantalla1
@Composable
fun PersonalDataScreen(
    viewModel: PersonalDataViewModel,
    onNext: () -> Unit
) {
    //orientacion y view model
    val uiState by viewModel.uiState.collectAsState()
    val isPortrait =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    FormScaffold(title = "Información personal") {
        if (isPortrait) {
            // Vertical
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                FirstNameField(uiState.firstName) { viewModel.updateFirstName(it) }
                LastNameField(uiState.lastName) { viewModel.updateLastName(it) }
                GenderField(uiState.gender) { viewModel.updateGender(it) }
                BirthdayField(uiState.birthDate) { viewModel.updateBirthDate(it) }
                EducationLevelSpinner(uiState.educationLevel) { viewModel.updateEducationLevel(it) }

                Button(
                    onClick = onNext,
                    enabled = uiState.isButtonEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Siguiente") }
            }
        } else {
            //Horizontal
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Parte de arriba nombre y apellido
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(Modifier.weight(1f)) {
                        FirstNameField(uiState.firstName) { viewModel.updateFirstName(it) }
                    }
                    Box(Modifier.weight(1f)) {
                        LastNameField(uiState.lastName) { viewModel.updateLastName(it) }
                    }
                }

                // Sexo en la mitad
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(Modifier.fillMaxWidth(0.6f)) {
                        GenderField(uiState.gender) { viewModel.updateGender(it) }
                    }
                }

                // Fecha en la mitad
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(Modifier.fillMaxWidth(0.6f)) {
                        BirthdayField(uiState.birthDate) { viewModel.updateBirthDate(it) }
                    }
                }

                // Parte final escolaridad
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(Modifier.fillMaxWidth(0.6f)) {
                        EducationLevelSpinner(uiState.educationLevel) { viewModel.updateEducationLevel(it) }
                    }
                }

                // Boton
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onNext,
                        enabled = uiState.isButtonEnabled
                    ) { Text("Siguiente") }
                }
            }
        }
    }
}

//Pantalla 2
@Composable
fun ContactDataScreen(
    viewModel: PersonalDataViewModel,
    onNext: () -> Unit
) {
    //orientación y view model
    val uiState by viewModel.uiState.collectAsState()
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    FormScaffold(title = "Datos de contacto") {
        if (isPortrait) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PhoneField(uiState.phone) { viewModel.updatePhone(it) }
                AddressField(uiState.address) { viewModel.updateAddress(it) }
                EmailField(uiState.email) { viewModel.updateEmail(it) }
                CountryDropdown(uiState.country) { viewModel.updateCountry(it) }
                CityDropdown(uiState.city) { viewModel.updateCity(it) }

                Button(
                    onClick = onNext,
                    enabled = uiState.isContactButtonEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Siguiente") }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    PhoneField(uiState.phone) { viewModel.updatePhone(it) }
                    AddressField(uiState.address) { viewModel.updateAddress(it) }
                    EmailField(uiState.email) { viewModel.updateEmail(it) }
                }
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    CountryDropdown(uiState.country) { viewModel.updateCountry(it) }
                    CityDropdown(uiState.city) { viewModel.updateCity(it) }
                    Button(
                        onClick = onNext,
                        enabled = uiState.isContactButtonEnabled,
                        modifier = Modifier.align(Alignment.End)
                    ) { Text("Siguiente") }
                }
            }
        }
    }
}

//Pantalla 3
@Composable
fun SummaryScreen(
    viewModel: PersonalDataViewModel,
    onEdit: () -> Unit
) {
    //orientación y view model
    val uiState by viewModel.uiState.collectAsState()
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    //Datos completos
    FormScaffold(title = "Resumen de datos") {
        if (isPortrait) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SummaryCard("Datos personales") {
                    Text("Nombre: ${uiState.firstName} ${uiState.lastName}")
                    Text("Sexo: ${uiState.gender}")
                    Text("Nacimiento: ${uiState.birthDate}")
                    Text("Escolaridad: ${uiState.educationLevel}")
                }
                SummaryCard("Datos de contacto") {
                    Text("Teléfono: ${uiState.phone}")
                    Text("Dirección: ${uiState.address}")
                    Text("Email: ${uiState.email}")
                    Text("País: ${uiState.country}")
                    Text("Ciudad: ${uiState.city}")
                }
                Button(onClick = onEdit, modifier = Modifier.fillMaxWidth()) {
                    Text("Editar datos")
                }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                SummaryCard("Datos personales", Modifier.weight(1f)) {
                    Text("Nombre: ${uiState.firstName} ${uiState.lastName}")
                    Text("Sexo: ${uiState.gender}")
                    Text("Nacimiento: ${uiState.birthDate}")
                    Text("Escolaridad: ${uiState.educationLevel}")
                }
                SummaryCard("Datos de contacto", Modifier.weight(1f)) {
                    Text("Teléfono: ${uiState.phone}")
                    Text("Dirección: ${uiState.address}")
                    Text("Email: ${uiState.email}")
                    Text("País: ${uiState.country}")
                    Text("Ciudad: ${uiState.city}")
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = onEdit, modifier = Modifier.align(Alignment.End)) {
                Text("Editar datos")
            }
        }
    }
}

//Titulito
@Composable
fun FormScaffold(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

//Cuadrito pantalla final
@Composable
fun SummaryCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier) {
        Column(Modifier.padding(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}
