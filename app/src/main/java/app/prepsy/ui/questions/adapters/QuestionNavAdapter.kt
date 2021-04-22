package app.prepsy.ui.questions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import app.prepsy.R


class QuestionNavAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val questions: List<Int>,
    private val onQuestionSelected: (Int) -> Unit
) : ArrayAdapter<Int>(context, layoutResource) {

    override fun getCount(): Int = questions.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: inflater.inflate(layoutResource, null)

        view.findViewById<TextView>(R.id.questionIndex).apply {
            text = questions[position].toString()
            background = when ((0..1).random()) {
                0 -> ContextCompat.getDrawable(
                        context,
                        R.drawable.drawable_question_answered_background
                    )
                else -> ContextCompat.getDrawable(
                            context,
                            R.drawable.drawable_question_unanswered_background
                        )
            }
            setOnClickListener { onQuestionSelected(position) }
        }
        return view
    }
}