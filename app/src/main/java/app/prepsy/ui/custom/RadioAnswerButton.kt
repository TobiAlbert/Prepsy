package app.prepsy.ui.custom

import android.content.Context
import android.util.AttributeSet

class RadioAnswerButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CheckableAnswerButton(context, attrs, defStyleAttr) {

    override fun performClick(): Boolean {
        if (isChecked) return false
        return super.performClick()
    }
}