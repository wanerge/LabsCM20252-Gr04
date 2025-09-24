package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.theme.Labs20252Gr04Theme

class PersonalDataActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20252Gr04Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PersonalDataScreen()
                }
            }
        }
    }
}