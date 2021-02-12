package app.prepsy.utils

import android.content.Context
import android.content.Intent
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

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