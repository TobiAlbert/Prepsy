package app.prepsy.ui.questions.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import app.prepsy.R
import app.prepsy.databinding.DialogQuestionNavigationBinding
import app.prepsy.ui.models.Question
import app.prepsy.ui.questions.QuestionViewModel
import app.prepsy.ui.questions.adapters.QuestionNavAdapter
import java.util.ArrayList
import javax.inject.Inject

class QuestionNavigationDialog : AppCompatDialogFragment() {

    @Inject lateinit var questionViewModel: QuestionViewModel

    var onQuestionSelected: ((Int) -> Unit)? = null
        set(value) {
            field = value
        }

    private var _binding: DialogQuestionNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val builder = AlertDialog.Builder(context)

        val inflater = LayoutInflater.from(context)
        _binding = DialogQuestionNavigationBinding.inflate(inflater)
        val view = binding.root

        binding.closeBtn.setOnClickListener { this.dismiss() }

        arguments?.let {
            val questions = it.getParcelableArrayList<Question>(QUESTIONS_KEY)

            questions ?: return@let

            // initialize the adapter
            val adapter = QuestionNavAdapter(
                context = context,
                layoutResource = R.layout.grid_item_question_nav,
                questions = questions.toList(),
                onQuestionSelected = ::questionSelected
            )

            binding.questionNavGridView.adapter = adapter
        }

        builder.setView(view)
        return builder.create()
    }

    companion object {
        const val QUESTIONS_KEY = "questions"

        fun newInstance(questions: List<Question>): QuestionNavigationDialog {
            val fragment = QuestionNavigationDialog()
            val bundle = Bundle().apply { putParcelableArrayList(QUESTIONS_KEY, ArrayList(questions)) }
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun questionSelected(position: Int) {
        onQuestionSelected?.invoke(position)
        this.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}