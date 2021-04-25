package app.prepsy.ui.questions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import app.prepsy.R
import app.prepsy.ui.models.Question
import app.prepsy.utils.getDrawableCompat


class QuestionNavAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val questions: List<Question>,
    private val onQuestionSelected: (Int) -> Unit
) : ArrayAdapter<Int>(context, layoutResource) {

    override fun getCount(): Int = questions.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: inflater.inflate(layoutResource, null)

        view.findViewById<TextView>(R.id.questionIndex).apply {
            val hasAnswer = questions[position].userOptionId != null
            text = position.inc().toString()
            background = when (hasAnswer) {
                true -> context.getDrawableCompat(R.drawable.drawable_question_answered_background)
                else -> null
            }
            setOnClickListener { onQuestionSelected(position) }
        }
        return view
    }
}