package app.prepsy.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.prepsy.MainActivity
import app.prepsy.utils.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<MainActivity>()
    }
}