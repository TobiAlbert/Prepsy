package app.prepsy.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IdRes

class AnswerRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var checkedId = View.NO_ID
    private var protectFromCheckedChange = false

    private var passThroughListener: PassThroughHierarchyChangeListener? = null
    private var childOnCheckedChangeListener: CheckableAnswerButton.OnCheckedChangeListener? = null

    init {
        childOnCheckedChangeListener = CheckedStateTracker()
        passThroughListener = PassThroughHierarchyChangeListener()

        super.setOnHierarchyChangeListener(passThroughListener)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is RadioAnswerButton) {
            if (child.isChecked) {
                protectFromCheckedChange = true
                if (checkedId != View.NO_ID) {
                    setCheckedStateForView(checkedId, false)
                }
                protectFromCheckedChange = false
                setCheckedId(child.id)
            }
        }

        super.addView(child, index, params)
    }

    private fun setCheckedStateForView(viewId: Int, checked: Boolean) {
        val checkedView = findViewById<View>(viewId)
        if (checkedView != null && checkedView is RadioAnswerButton) {
            checkedView.isChecked = checked
        }
    }

    private fun setCheckedId(@IdRes id: Int) {
        checkedId = id
    }

    private inner class CheckedStateTracker : CheckableAnswerButton.OnCheckedChangeListener {
        override fun onCheckedChanged(view: View, isChecked: Boolean) {
            // prevents from infinite recursion
            if (protectFromCheckedChange) {
                return
            }

            protectFromCheckedChange = true
            if (checkedId != View.NO_ID) {
                setCheckedStateForView(checkedId, false)
            }
            protectFromCheckedChange = false

            val id = view.id
            setCheckedId(id)
        }
    }

    private inner class PassThroughHierarchyChangeListener : OnHierarchyChangeListener {
        var onHierarchyChangeListener: OnHierarchyChangeListener? = null

        override fun onChildViewAdded(parent: View, child: View) {
            if (parent === this@AnswerRadioGroup && child is CheckableAnswerButton) {
                var id = child.id
                // generates an id if it's missing
                if (id == View.NO_ID) {
                    id = View.generateViewId()
                    child.id = id
                }
                childOnCheckedChangeListener?.let {
                    child.addOnCheckChangeListener(it)
                }
            }

            onHierarchyChangeListener?.onChildViewAdded(parent, child)
        }

        override fun onChildViewRemoved(parent: View, child: View) {
            if (parent === this@AnswerRadioGroup && child is CheckableAnswerButton) {
                childOnCheckedChangeListener?.let {
                    child.removeOnCheckChangeListener(it)
                }
            }
            onHierarchyChangeListener?.onChildViewRemoved(parent, child)
        }
    }
}