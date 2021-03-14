package app.prepsy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.prepsy.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).apply {
            setContentView(root)

            @Suppress("DEPRECATION")
            rootView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
    }

}