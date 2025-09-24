package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.theme.Labs20252Gr04Theme

class PersonalDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20252Gr04Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Lab1App()
                }
            }
        }
    }
}
