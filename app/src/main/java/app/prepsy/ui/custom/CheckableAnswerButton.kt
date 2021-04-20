package app.prepsy.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import androidx.core.content.ContextCompat
import androidx.core.view.children
import app.prepsy.R

open class CheckableAnswerButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AnswerButton(context, attrs, defStyleAttr), Checkable {

    private var checked: Boolean = false
    interface OnCheckedChangeListener {
        fun onCheckedChanged(view: View, isChecked: Boolean)
    }

    private val listeners = mutableListOf<OnCheckedChangeListener>()

    init {
        background = ContextCompat.getDrawable(context, R.drawable.checkable_background_selector)
    }

    override fun isChecked(): Boolean = checked

    override fun toggle() {
        isChecked = !checked
    }

    override fun setChecked(checked: Boolean) {
        if (this.checked != checked) {
            this.checked = checked
            children.filter { it is Checkable }.forEach { (it as Checkable).isChecked = checked }
            listeners.forEach { it.onCheckedChanged(this, this.checked) }
            refreshDrawableState()
        }
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    fun addOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        listeners.add(onCheckedChangeListener)
    }

    fun removeOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        listeners.remove(onCheckedChangeListener)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }
}