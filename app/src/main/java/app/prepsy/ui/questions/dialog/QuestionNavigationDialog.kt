package app.prepsy.ui.questions.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import app.prepsy.R
import app.prepsy.databinding.DialogQuestionNavigationBinding
import app.prepsy.ui.questions.QuestionViewModel
import app.prepsy.ui.questions.adapters.QuestionNavAdapter
import javax.inject.Inject

class QuestionNavigationDialog(
    private val onQuestionSelected: (Int) -> Unit,
    private val onDismiss: (QuestionNavigationDialog) -> Unit,
) : AppCompatDialogFragment() {

    @Inject lateinit var questionViewModel: QuestionViewModel
    private var _binding: DialogQuestionNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val builder = AlertDialog.Builder(context)

        val inflater = LayoutInflater.from(context)
        _binding = DialogQuestionNavigationBinding.inflate(inflater)
        val view = binding.root

        binding.closeBtn.setOnClickListener { onDismiss(this) }
        val dummyQuestions = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
        )

        // initialize the adapter
        val adapter = QuestionNavAdapter(
            context = context,
            layoutResource = R.layout.grid_item_question_nav,
            questions = dummyQuestions,
            onQuestionSelected = ::questionSelected
        )

        binding.questionNavGridView.adapter = adapter

        builder.setView(view)
        return builder.create()
    }

    private fun questionSelected(position: Int) {
        onQuestionSelected(position)
        onDismiss(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}