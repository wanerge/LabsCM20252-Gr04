package co.edu.udea.compumovil.labs20252_gr04.lab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.personaldata.PersonalDataActivity
import co.edu.udea.compumovil.labs20252_gr04.lab1.ui.theme.Labs20252Gr04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labs20252Gr04Theme {
                val intent = Intent(this@MainActivity, PersonalDataActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}