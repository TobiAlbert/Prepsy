package app.prepsy.ui.home

import android.content.Context
import android.widget.ArrayAdapter
import app.prepsy.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeAdapter <T> @Inject constructor(
    @ApplicationContext context: Context
): ArrayAdapter<T>(context, R.layout.list_item_dropdown, mutableListOf()) {
    fun addAll(items: List<T>) {
        clear()
        super.addAll(items)
    }
}