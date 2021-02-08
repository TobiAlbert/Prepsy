package app.prepsy.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

// Activity / Context Extensions
inline fun <reified T: AppCompatActivity> Context.startActivity() {
    this.startActivity(Intent(this, T::class.java))
}