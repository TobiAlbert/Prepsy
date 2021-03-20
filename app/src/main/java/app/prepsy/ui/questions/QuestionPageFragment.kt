package app.prepsy.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.prepsy.R
import app.prepsy.databinding.FragmentQuestionPageBinding
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.managers.SharedPreferenceManagers.Companion.HAS_DOUBLE_CLICKED
import app.prepsy.managers.SharedPreferenceManagers.Companion.HAS_SWIPED
import app.prepsy.ui.models.Question
import app.prepsy.ui.questions.adapters.QuestionPageAdapter
import app.prepsy.utils.onPageSelected
import app.prepsy.utils.showActionSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class QuestionPageFragment : Fragment() {
    @Inject lateinit var sharedPrefsManager: SharedPreferenceManagers
    private val questionViewModel: QuestionViewModel by viewModels()
    private val args: QuestionPageFragmentArgs by navArgs()

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
        questionViewModel.getTestQuestions(args.args.subjectId, args.args.yearId)
        setupUi()
        observeViewModel()
    }

    private fun setupUi() {
        // setup toolbar
        binding.toolbar.inflateMenu(R.menu.menu_question)
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_submit -> {
                    Toast.makeText(
                        requireContext(),
                        it.title,
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
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

    private fun observeViewModel() {
        questionViewModel.getQuestions().observe(viewLifecycleOwner, Observer {
            it?.let { questions: List<Question> ->

                val questionPageAdapter = QuestionPageAdapter(this@QuestionPageFragment, questions)
                val numOfQuestions = questions.size

                binding.viewpager.adapter = questionPageAdapter
                binding.viewpager.onPageSelected { position: Int ->
                    val currentQuestionIndex = position + 1

                    binding.progressBar.progress = (currentQuestionIndex * 100 / numOfQuestions)
                    binding.questionNumber.text =
                        getString(R.string.page_question_number, currentQuestionIndex, numOfQuestions)
                }
            }
        })
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