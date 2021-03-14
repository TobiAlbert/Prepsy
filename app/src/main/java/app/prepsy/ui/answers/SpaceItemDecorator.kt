package app.prepsy.ui.answers

import android.graphics.Rect
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class SpaceItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val index = parent.indexOfChild(view)
        val textView = view as MaterialTextView

        if (index % 3 == 0) {
            textView.gravity = Gravity.START
            return
        }

        // if end item, put layout gravity to end
        if ((index + 1) % 3 == 0) {
            textView.gravity = Gravity.END
            view.setPadding(view.paddingStart, view.paddingTop, 0, view.paddingBottom)
            return
        }

        // if middle item, put layout gravity in middle
        textView.gravity = Gravity.CENTER_HORIZONTAL
    }
}