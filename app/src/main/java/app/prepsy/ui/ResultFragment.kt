package app.prepsy.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import app.prepsy.R
import app.prepsy.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

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
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.background_green)
        when ((0..1).random()) {
            0 -> setupSuccessPage(80, 100)
            1 -> setFailurePage(10, 100)
        }
    }

    private fun setupSuccessPage(score: Int, totalQuestions: Int) {
        binding.apply {
            val successColor = ContextCompat.getColor(requireContext(), R.color.button_green)
            resultImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.drawable_congrats, null))
            resultTitle.text = getString(R.string.result_congrats_title)
            resultTitle.setTextColor(successColor)
            resultMessage.text = getString(R.string.result_congrats_message)
            scoreValue.text = "$score/$totalQuestions"
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
            scoreValue.text = "$score/$totalQuestions"
            viewSheetBtn.setTextColor(failureColor)
            takeExamAgainBtn.text = getString(R.string.try_again_button_text)
            takeExamAgainBtn.setBackgroundColor(failureColor)
        }
    }
}