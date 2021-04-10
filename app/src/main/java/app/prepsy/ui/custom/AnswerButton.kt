package app.prepsy.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import app.prepsy.R

open class AnswerButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.answer_radio_button_layout, this, true)
        val resources = context.resources
        val params = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, resources.displayMetrics).toInt()
        val cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4F, resources.displayMetrics)

        params.setMargins(0, margin, 0, margin)
        layoutParams = params

        radius = cornerRadius

        isClickable = true
        isFocusable = true

        background = ContextCompat.getDrawable(context, R.drawable.background_selector)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }

    fun setOption(alphabet: String, text: String) {
        findViewById<TextView>(R.id.optionAlphabet).text = alphabet
        findViewById<TextView>(R.id.optionText).text = text
    }
}