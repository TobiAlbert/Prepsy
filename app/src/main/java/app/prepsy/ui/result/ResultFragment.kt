package app.prepsy.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.prepsy.R
import app.prepsy.databinding.FragmentResultBinding
import app.prepsy.ui.models.args.QuestionPageFragmentPayload
import app.prepsy.ui.models.args.QuestionPageMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private val resultViewModel: ResultViewModel by viewModels()

    private var _binding: FragmentResultBinding? = null
    private val args: ResultFragmentArgs by navArgs()
    private val binding get() = _binding!!

    companion object {
        private const val CUT_OFF_PERCENTAGE = .4
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        val score = args.args.userScore.score
        val total = args.args.userScore.total

        val subjectId = args.args.subjectId
        val yearId = args.args.yearId

        // handle back button pressed
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToQuestions(
                        mode = QuestionPageMode.QUESTION_MODE,
                        subjectId = subjectId,
                        yearId = yearId
                    )
                }
            })

        when (score / total > CUT_OFF_PERCENTAGE) {
            true -> setupSuccessPage(score, total)
            false -> setFailurePage(score, total)
        }

        binding.viewSheetBtn.setOnClickListener {
            navigateToQuestions(
                mode = QuestionPageMode.VIEW_ANSWER_MODE,
                subjectId = subjectId,
                yearId = yearId
            )
        }

        binding.homeBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_resultFragment_to_homeFragment
            )
        }

        binding.takeExamAgainBtn.setOnClickListener {

            resultViewModel.clearUserAnswersForTest(
                subjectId = subjectId,
                yearId = yearId
            ).observe(viewLifecycleOwner, Observer {
                if (it) {
                    navigateToQuestions(
                        mode = QuestionPageMode.QUESTION_MODE,
                        subjectId = subjectId,
                        yearId = yearId
                    )
                }
            })
        }
    }

    private fun navigateToQuestions(mode: QuestionPageMode, subjectId: String, yearId: String) {
        val args = QuestionPageFragmentPayload(
            mode = mode,
            subjectId = subjectId,
            yearId = yearId
        )

        val action =
            ResultFragmentDirections
                .actionResultFragmentToQuestionPageFragment(args)

        findNavController().navigate(action)
    }

    private fun setupSuccessPage(score: Int, totalQuestions: Int) {
        binding.apply {
            val successColor = ContextCompat.getColor(requireContext(), R.color.button_green)
            resultImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.drawable_congrats, null))
            resultTitle.text = getString(R.string.result_congrats_title)
            resultTitle.setTextColor(successColor)
            resultMessage.text = getString(R.string.result_congrats_message)
            scoreValue.text = getString(R.string.final_score, score, totalQuestions)
            viewSheetBtn.setTextColor(successColor)
            takeExamAgainBtn.text = getString(R.string.take_exam_again_button_text)
            takeExamAgainBtn.setBackgroundColor(successColor)
        }
    }

    private fun setFailurePage(score: Int, totalQuestions: Int) {
        binding.apply {
            val failureColor = ContextCompat.getColor(requireContext(), R.color.button_red)
            resultImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.drawable_try_again, null))
            resultTitle.text = getString(R.string.result_try_again_title)
            resultTitle.setTextColor(failureColor)
            resultMessage.text = getString(R.string.result_try_again_message)
            scoreValue.text = getString(R.string.final_score, score, totalQuestions)
            viewSheetBtn.setTextColor(failureColor)
            takeExamAgainBtn.text = getString(R.string.try_again_button_text)
            takeExamAgainBtn.setBackgroundColor(failureColor)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}