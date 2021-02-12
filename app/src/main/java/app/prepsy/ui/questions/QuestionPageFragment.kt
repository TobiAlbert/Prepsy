package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentQuestionPageBinding
import app.prepsy.ui.models.questions
import app.prepsy.ui.questions.adapters.QuestionPageAdapter
import app.prepsy.utils.onPageSelected

class QuestionPageFragment : Fragment() {
    private var _binding: FragmentQuestionPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.question_background)

        val questionPageAdapter = QuestionPageAdapter(this@QuestionPageFragment, questions)
        val numOfQuestions = questions.size
        binding.questionProgressIndicator.max = numOfQuestions

        binding.questionsViewPager.adapter = questionPageAdapter
        binding.questionsViewPager.onPageSelected { position: Int ->
            val currentQuestionIndex = position + 1
            binding.questionProgressIndicator.progress = currentQuestionIndex
            binding.questionNumber.text =
                getString(R.string.page_question_number, currentQuestionIndex, numOfQuestions)
        }

        binding.homeBackBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_questionPageFragment_to_homeFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}