package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import co.edu.udea.compumovil.labs20252_gr04.lab1.R

// Campitos

// Nombre
@Composable
fun FirstNameField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.first_name)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            autoCorrectEnabled = false
        )
    )
}

// Apellido
@Composable
fun LastNameField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.last_name)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            autoCorrectEnabled = false
        )
    )
}

// Campo de género
@Composable
fun GenderField(selectedOption: String, onOptionChange: (String) -> Unit) {
    val options = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female),
        stringResource(R.string.other)
    )

    Column(Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.gender), style = MaterialTheme.typography.titleMedium)
        Row(
            Modifier.fillMaxWidth().selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { text ->
                Row(
                    Modifier.selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            if (text == selectedOption) {
                                // Si está seleccionado, deselecciona enviando una cadena vacía
                                onOptionChange("")
                            } else {
                                // Si no está seleccionado, selecciona esta nueva opción
                                onOptionChange(text)
                            }
                        },
                        role = Role.RadioButton
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(text)
                }
            }
        }
    }
}

// Campo de fecha de nacimiento
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayField(selectedDate: String, onDateChange: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(stringResource(R.string.birth_date)) },
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            TextButton(onClick = { showDialog = true }) {
                Text(stringResource(R.string.change))
            }
        }
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val instant = Instant.ofEpochMilli(millis)
                        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                        onDateChange(localDate.toString())
                    }
                    showDialog = false
                }) {
                    Text(stringResource(R.string.okay))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// Campo de escolaridad
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationLevelSpinner(selectedOption: String, onOptionChange: (String) -> Unit) {
    // Definimos las opciones estáticas
    val staticOptions = listOf(
        stringResource(R.string.elementary),
        stringResource(R.string.high_school),
        stringResource(R.string.university),
        stringResource(R.string.other)
    )

    val clearOptionText = stringResource(R.string.none_select)
    val allOptions = listOf(clearOptionText) + staticOptions

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = if (selectedOption.isBlank()) clearOptionText else selectedOption,
            onValueChange = {},
            label = { Text(stringResource(R.string.education_level)) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            allOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        if (option == clearOptionText) {
                            onOptionChange("")
                        } else {
                            onOptionChange(option)
                        }
                        expanded = false
                    },
                    enabled = option != clearOptionText || selectedOption.isNotBlank()
                )
            }
        }
    }
}

// ------------------ CAMPOS DE PANTALLA 2 ------------------

// Teléfono
@Composable
fun PhoneField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.phone)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        )
    )
}

// Dirección
@Composable
fun AddressField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.address)) },
        singleLine = false,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next,
            autoCorrectEnabled = false
        )
    )
}

// Email
@Composable
fun EmailField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.email)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropdown(selectedOption: String, onOptionChange: (String) -> Unit) {
    val options = listOf(
        stringResource(R.string.colombia),
        stringResource(R.string.mexico),
        stringResource(R.string.argentina),
        stringResource(R.string.other)
    )
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(stringResource(R.string.country)) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropdown(selectedOption: String, onOptionChange: (String) -> Unit) {
    // Opciones base del menú (usando la simulación de stringResource)
    val staticOptions = listOf(
        stringResource(R.string.medellin),
        stringResource(R.string.bogota),
        stringResource(R.string.cali),
        stringResource(R.string.other)
    )

    // Opción que representa la deselección (envía "" al ViewModel)
    val clearOptionText = stringResource(R.string.none_select)
    // Lista completa, incluyendo la opción para borrar
    val allOptions = listOf(clearOptionText) + staticOptions

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            // Muestra el valor seleccionado o el texto de "Borrar Selección" si está vacío.
            value = if (selectedOption.isBlank()) clearOptionText else selectedOption,
            onValueChange = {},
            label = { Text(stringResource(R.string.city)) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            allOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        // LÓGICA DE DESELECCIÓN:
                        if (option == clearOptionText) {
                            onOptionChange("") // Si se selecciona "Borrar", envía cadena vacía
                        } else {
                            onOptionChange(option) // Envía la ciudad seleccionada
                        }
                        expanded = false
                    },
                    // Deshabilita la opción de "Borrar Selección" si el campo ya está vacío
                    enabled = option != clearOptionText || selectedOption.isNotBlank()
                )
            }
        }
    }
}
