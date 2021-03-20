package app.prepsy.ui.questions.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.prepsy.databinding.ListItemQuestionOptionsBinding
import app.prepsy.ui.models.Option
import app.prepsy.utils.toAlphabet


class OptionViewHolder(
    context: Context,
    private val binding: ListItemQuestionOptionsBinding,
    private val onOptionSelected: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        val onDoubleTapListener = object: GestureDetector.OnDoubleTapListener {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                onOptionSelected(binding.questionOptionAlphabet.text.toString())
                return true
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean = false
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean = false
        }

        val onGestureDetectorListener = object: GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) = Unit
            override fun onSingleTapUp(e: MotionEvent?): Boolean = false
            override fun onDown(e: MotionEvent?): Boolean = false
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean = false

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean = false

            override fun onLongPress(e: MotionEvent?) = Unit
        }

        val gestureDetector = GestureDetector(context, onGestureDetectorListener)
        gestureDetector.setOnDoubleTapListener(onDoubleTapListener)

        @SuppressLint("ClickableViewAccessibility")
        val onTouchListener = View.OnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            return@OnTouchListener true
        }

        binding.root.setOnTouchListener(onTouchListener)
    }

    fun bind(option: Option, position: Int) = with(binding) {
        questionOptionAlphabet.text = position.inc().toAlphabet()
        questionOptionText.text = option.text
        Unit
    }
}