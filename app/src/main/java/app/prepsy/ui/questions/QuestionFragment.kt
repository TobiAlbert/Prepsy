package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.prepsy.R
import app.prepsy.databinding.FragmentQuestionBinding
import app.prepsy.ui.custom.RadioAnswerButton
import app.prepsy.ui.models.Option
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.args.QuestionPageMode
import app.prepsy.utils.getDrawableCompat
import app.prepsy.utils.toAlphabet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private val questionViewModel: QuestionViewModel by viewModels()
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(QUESTIONS_KEY) && it.containsKey(QUESTION_PAGE_MODE_KEY) }?.apply {
            val question = this.getParcelable<Question>(QUESTIONS_KEY) ?: return@apply
            val mode = this.getSerializable(QUESTION_PAGE_MODE_KEY) as QuestionPageMode

            // setup the ui
            binding.questionText.text = question.text

            // setup radio groups
            val userOptionId: String? = question.userOptionId

            question.options.forEachIndexed { index: Int, option: Option ->
                RadioAnswerButton(requireContext()).apply {
                    id = View.generateViewId()
                    setOption(index.inc().toAlphabet(), option.text)
                    setOnClickListener { onOptionSelected(question.id, option.id) }
                    binding.optionsRadioGroup.addView(this)

                    isEnabled = mode == QuestionPageMode.QUESTION_MODE

                    // set option background
                    when(mode) {
                        QuestionPageMode.QUESTION_MODE -> {
                            if (userOptionId == option.id) this.isChecked = true
                        }

                        QuestionPageMode.VIEW_ANSWER_MODE -> {
                            background = when {
                                question.answer.id == option.id ->
                                    requireContext().getDrawableCompat(R.drawable.correct_answer_selector)
                                userOptionId == option.id ->
                                    requireContext().getDrawableCompat(R.drawable.wrong_answer_selector)
                                else -> null
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onOptionSelected(questionId: String, optionId: String) {
        questionViewModel.saveAnswer(questionId, optionId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val QUESTION_PAGE_MODE_KEY = "question_mode"
        const val QUESTIONS_KEY = "questions"

        @JvmStatic
        fun newInstance(question: Question, mode: QuestionPageMode): QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle().apply {
                putParcelable(QUESTIONS_KEY, question)
                putSerializable(QUESTION_PAGE_MODE_KEY, mode)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

}