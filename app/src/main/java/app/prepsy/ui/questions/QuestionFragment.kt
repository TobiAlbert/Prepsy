package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.prepsy.databinding.FragmentQuestionBinding
import app.prepsy.ui.custom.RadioAnswerButton
import app.prepsy.ui.models.Option
import app.prepsy.ui.models.Question
import app.prepsy.utils.toAlphabet
import dagger.hilt.android.AndroidEntryPoint

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

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
        arguments?.takeIf { it.containsKey(ARG_PARAM1) }?.apply {
            val question = this.getParcelable<Question>(ARG_PARAM1) ?: return@apply

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

                    if (userOptionId == option.id) this.isChecked = true
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
        @JvmStatic
        fun newInstance(question: Question): QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle().apply { putParcelable(ARG_PARAM1, question) }
            fragment.arguments = bundle
            return fragment
        }
    }

}