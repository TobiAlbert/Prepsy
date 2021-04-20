package app.prepsy.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import app.prepsy.R
import app.prepsy.ui.models.Subject
import com.google.android.material.snackbar.Snackbar
import java.util.*

// Activity / Context Extensions
inline fun <reified T: AppCompatActivity> Context.startActivity() {
    this.startActivity(Intent(this, T::class.java))
}

fun Fragment.getColorCompat(@ColorRes color: Int) = this.requireContext().getColorCompat(color)
fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

// View Pager Extensions
fun ViewPager2.onPageSelected(callback: (position: Int) -> Unit) {
    this.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            callback(position)
        }
    })
}

fun View.showActionSnackBar(
    @StringRes message: Int,
    @StringRes actionText: Int,
    action: (view: View) -> Unit) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText, action)
        .show()
}

// String Extensions
fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.toLowerCase(Locale.US).capitalize(Locale.US) }

// Int Extensions
fun Int.toAlphabet(): String {

    val maxLetterInt = 90
    val baseInt = 64

    val value = if (this + baseInt > maxLetterInt) maxLetterInt else this

    return (baseInt + value).toChar().toString()
}
