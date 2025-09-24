package co.edu.udea.compumovil.labs20252_gr04.lab1.ui.contactdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.theme.Labs20252Gr04Theme

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20252Gr04Theme {
                Text(text = "Hello Contact Data Activity")
            }
        }
    }
}