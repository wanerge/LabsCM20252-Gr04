package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import android.app.DatePickerDialog
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.*
import co.edu.udea.compumovil.labs20252_gr04.lab1.R
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.contactdata.ContactDataActivity
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.dimensionResource
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.semantics.Role
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.theme.Labs20252Gr04Theme
import java.time.Instant
import java.time.ZoneId
import androidx.compose.ui.composed

@Composable
fun PersonalDataScreen(
    personalDataViewModel: PersonalDataViewModel = viewModel()
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val personalDataUiState by personalDataViewModel.uiState.collectAsState()

    val focusRequester = remember { FocusRequester() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de Nombres (Obligatorio)
            Text(text = stringResource(R.string.personal_information))

            OutlinedTextField(
                value = personalDataUiState.firstName,
                onValueChange = { personalDataViewModel.updateFirstName(it) },
                label = { Text(text = stringResource(R.string.first_name)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de Apellidos (Obligatorio)
            OutlinedTextField(
                value = personalDataUiState.lastName,
                onValueChange = { personalDataViewModel.updateLastName(it) },
                label = { Text(text = stringResource(R.string.last_name)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de Sexo (Opcional)
            GenderField(
                selectedOption = personalDataUiState.gender,
                onOptionChange = { personalDataViewModel.updateGender(it) }
            )

            // Campo de Fecha de nacimiento (Obligatorio)
            BirthdayField(
                selectedDate = personalDataUiState.birthDate,
                onDateChange = { personalDataViewModel.updateBirthDate(it) }
            )

            // Campo de Grado de escolaridad (Opcional)
            EducationLevelSpinner(
                selectedOption = personalDataUiState.educationLevel,
                onOptionChange = { personalDataViewModel.updateEducationLevel(it) }
            )

            Spacer(Modifier.height(mediumPadding))

            // Botón de Siguiente
            Button(
                onClick = { } ,
                enabled = personalDataUiState.isButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

// Composable para el campo de Sexo
@Composable
fun GenderField(
    selectedOption: String,
    onOptionChange: (String) -> Unit
) {
    val radioOptions = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female),
        stringResource(R.string.other)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.gender), style = typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth().selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            radioOptions.forEach { text ->
                Row(
                    Modifier.selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionChange(text) },
                        role = Role.RadioButton
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // El clic se maneja en el Row
                    )
                    Text(text = text)
                }
            }
        }
    }
}

// Composable para el campo de Fecha de nacimiento
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayField(
    selectedDate: String,
    onDateChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { /* Solo lectura, el valor cambia por el DatePicker */ },
        label = { Text(text = stringResource(R.string.birth_date)) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(remember { FocusRequester() })
            .noRippleClickable {
                focusManager.clearFocus()
                showDatePicker = true
            }
    )

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val dateMillis = datePickerState.selectedDateMillis
                    if (dateMillis != null) {
                        val instant = Instant.ofEpochMilli(dateMillis)
                        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                        onDateChange(localDate.toString())
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// Composable para el campo de Grado de escolaridad (Spinner)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationLevelSpinner(
    selectedOption: String,
    onOptionChange: (String) -> Unit
) {
    val options = listOf(
        stringResource(R.string.elementary),
        stringResource(R.string.high_school),
        stringResource(R.string.technical),
        stringResource(R.string.university),
        stringResource(R.string.postgraduate)
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
            readOnly = true,
            label = { Text(text = stringResource(R.string.education_level)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
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

// Función de extensión para hacer un Composable clicable sin efecto de onda
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) { onClick() }
}
