package app.prepsy.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentHomeBinding
import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.Year
import app.prepsy.ui.models.args.QuestionPageFragmentPayload
import app.prepsy.ui.models.args.QuestionPageMode
import app.prepsy.utils.displayAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var mSubjectAdapter: HomeAdapter<Subject>

    @Inject
    lateinit var mYearAdapter: HomeAdapter<Year>

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeViewModel()
    }

    private fun setupUi() {

        binding.selectSubjectAT.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                homeViewModel.onSubjectSelected(position)
            }

        binding.selectYearAT.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                homeViewModel.onYearSelected(position)
            }

        binding.viewQuestionsBtn.setOnClickListener {
            homeViewModel.viewQuestions()
        }

        val paddingTop = binding.textView.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(binding.textView) { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + paddingTop)
            insets
        }
    }

    private fun showQuestionInProgressDialog(subjectId: String, yearId: String) {
        val onPositiveClicked = { _: DialogInterface, _: Int ->
            homeViewModel.navigateToQuestions(
                subjectId = subjectId,
                yearId = yearId
            )
        }

        val onNegativeClicked = { _: DialogInterface, _: Int ->
            homeViewModel.clearUserAnswersForTest(
                subjectId = subjectId,
                yearId = yearId
            )
        }

        displayAlertDialog(
            context = requireContext(),
            message = R.string.question_in_progress_dialog_message,
            positiveButtonText = R.string.question_in_progress_dialog_positive_button_text,
            negativeButtonText = R.string.question_in_progress_dialog_negative_button_text,
            onPositiveButtonClicked = onPositiveClicked,
            onNegativeButtonClicked = onNegativeClicked
        )
    }

    private fun navigateToQuestions(subjectId: String, yearId: String) {
        val args = QuestionPageFragmentPayload(
            mode = QuestionPageMode.QUESTION_MODE,
            subjectId = subjectId,
            yearId = yearId
        )

        val action =
            HomeFragmentDirections.actionHomeFragmentToQuestionPageFragment(args)

        findNavController().navigate(action)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch { homeViewModel.state.collect(::handleViewState) }
                launch { homeViewModel.action.collect(::handleViewAction) }
            }
        }
    }

    private fun setupSubjectsAdapter(subjects: List<Subject>, selectedSubject: String) {
        if (subjects.isEmpty()) return

        mSubjectAdapter.addAll(subjects)

        binding.selectSubjectAT.setAdapter(mSubjectAdapter)

        binding.selectSubjectAT.setText(selectedSubject, false)
    }

    private fun setupYearsAdapter(years: List<Year>, selectedYear: String) {
        if (years.isEmpty()) return

        mYearAdapter.addAll(years)

        binding.selectYearAT.setAdapter(mYearAdapter)

        binding.selectYearAT.setText(selectedYear, false)
    }

    private fun handleViewState(state: HomeViewState) {

        binding.viewQuestionsBtn.isEnabled = state.isButtonEnabled

        setupYearsAdapter(
            years = state.years,
            selectedYear = state.selectedYear
        )

        setupSubjectsAdapter(
            subjects = state.subjects,
            selectedSubject = state.selectedSubject
        )
    }

    private fun handleViewAction(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.NavigateToQuestions ->
                navigateToQuestions(
                    subjectId = action.subjectId,
                    yearId = action.yearId,
                )
            is HomeViewAction.DisplayDialogPrompt ->
                showQuestionInProgressDialog(
                    subjectId = action.subjectId,
                    yearId = action.yearId,
                )
        }
    }
}