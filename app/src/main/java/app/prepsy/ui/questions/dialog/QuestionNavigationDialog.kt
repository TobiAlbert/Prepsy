package app.prepsy.ui.questions.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import app.prepsy.R
import app.prepsy.databinding.DialogQuestionNavigationBinding
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.args.QuestionPageMode
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
            val mode = it.getSerializable(QUESTION_PAGE_MODE) as QuestionPageMode

            questions ?: return@let

            // initialize the adapter
            val adapter = QuestionNavAdapter(
                context = context,
                layoutResource = R.layout.grid_item_question_nav,
                questions = questions.toList(),
                mode = mode,
                onQuestionSelected = ::questionSelected
            )

            binding.questionNavGridView.adapter = adapter
        }

        builder.setView(view)
        return builder.create()
    }

    companion object {
        private const val QUESTIONS_KEY = "questions"
        private const val QUESTION_PAGE_MODE = "question_page_mode"

        fun newInstance(
            questions: List<Question>,
            mode: QuestionPageMode
        ): QuestionNavigationDialog {
            val fragment = QuestionNavigationDialog()
            val bundle = Bundle().apply {
                putParcelableArrayList(QUESTIONS_KEY, ArrayList(questions))
                putSerializable(QUESTION_PAGE_MODE, mode)
            }
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