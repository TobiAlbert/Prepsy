package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.prepsy.databinding.FragmentQuestionBinding
import app.prepsy.ui.models.Question
import app.prepsy.ui.questions.adapters.OptionAdapter

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class QuestionFragment : Fragment() {
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
            binding.optionsRv.apply {
                adapter = OptionAdapter(question.options)
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(question: Question): QuestionFragment  {
            val fragment = QuestionFragment()
            val bundle = Bundle().apply { putParcelable(ARG_PARAM1, question) }
            fragment.arguments = bundle
            return fragment
        }
    }

}