package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentQuestionPageBinding
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.managers.SharedPreferenceManagers.Companion.HAS_DOUBLE_CLICKED
import app.prepsy.managers.SharedPreferenceManagers.Companion.HAS_SWIPED
import app.prepsy.ui.models.questions
import app.prepsy.ui.questions.adapters.QuestionPageAdapter
import app.prepsy.utils.onPageSelected
import app.prepsy.utils.showActionSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class QuestionPageFragment : Fragment() {
    @Inject lateinit var sharedPrefsManager: SharedPreferenceManagers

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
        // setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val questionPageAdapter = QuestionPageAdapter(this@QuestionPageFragment, questions)
        val numOfQuestions = questions.size

        binding.viewpager.adapter = questionPageAdapter
        binding.viewpager.onPageSelected { position: Int ->
            val currentQuestionIndex = position + 1

            binding.progressBar.progress = (currentQuestionIndex * 100 / numOfQuestions)
            binding.questionNumber.text =
                getString(R.string.page_question_number, currentQuestionIndex, numOfQuestions)
        }

        val hasDoubleClicked: Boolean =
            sharedPrefsManager.getBoolean(HAS_DOUBLE_CLICKED)

        val hasSwiped: Boolean =
            sharedPrefsManager.getBoolean(HAS_SWIPED)

        val swipeCallback: (View) -> Unit = { sharedPrefsManager.saveBoolean(HAS_SWIPED, true)  }

        val doubleClickCallback: (View) -> Unit = {
            sharedPrefsManager.saveBoolean(HAS_DOUBLE_CLICKED, true)
            if (!hasSwiped) showSwipeInfoSnackBar(swipeCallback)
        }

        when {
            !hasDoubleClicked -> showDoubleClickInfoSnackBar(doubleClickCallback)
            !hasSwiped -> showSwipeInfoSnackBar(swipeCallback)
        }

        val topPadding = binding.appbar.paddingTop
        binding.appbar.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + topPadding)
            insets
        }
    }

    private fun showDoubleClickInfoSnackBar(callback: (View) -> Unit) {
        binding.root.showActionSnackBar(
            R.string.double_tap_message,
            R.string.double_tap_option_action_text,
            callback
        )
    }

    private fun showSwipeInfoSnackBar(callback: (View) -> Unit) {
        binding.root.showActionSnackBar(
            R.string.swipe_message,
            R.string.swipe_action_text,
            callback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}