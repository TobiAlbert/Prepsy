package app.prepsy.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentHomeBinding
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.SubjectWithYears
import app.prepsy.ui.models.Year
import app.prepsy.ui.models.args.QuestionPageFragmentPayload
import app.prepsy.ui.models.args.QuestionPageMode
import app.prepsy.utils.capitalizeWords
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var mSharedPreferenceManager: SharedPreferenceManagers

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mSubjects: List<Subject>
    private lateinit var mYears: List<Year>

    private lateinit var mSubjectAdapter: ArrayAdapter<Subject>
    private lateinit var mYearAdapter: ArrayAdapter<Year>

    private lateinit var mSelectedSubject: Subject
    private lateinit var mSelectedYear: Year

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeViewModel()
    }

    private fun setupUi() {

        mSubjectAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, mutableListOf())
        mYearAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, mutableListOf())

        binding.selectYearAT.setAdapter(mYearAdapter)

        binding.selectSubjectAT.setAdapter(mSubjectAdapter)
        binding.selectSubjectAT.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                if (::mSubjects.isInitialized) {
                    val subject: Subject = mSubjects[position]
                    mSelectedSubject = subject
                    homeViewModel.getAvailableYearsForSubject(subject.id)
                }
            }

        binding.selectYearAT.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                if (::mYears.isInitialized) {
                    mSelectedYear = mYears[position]
                    mSharedPreferenceManager.saveString(
                        SharedPreferenceManagers.LAST_SELECTED_YEAR_ID,
                        mYears[position].id
                    )
                }
            }

        binding.viewQuestionsBtn.setOnClickListener {
            // ensure there are loaded subjects
            if (!::mSubjects.isInitialized) return@setOnClickListener
            if (!::mYears.isInitialized) return@setOnClickListener

            // get current subject and year id
            val subjectId = mSelectedSubject.id
            val yearId = mSelectedYear.id


            // save the last selected subject id
            mSharedPreferenceManager.saveString(
                SharedPreferenceManagers.LAST_SELECTED_SUBJECT_ID,
                subjectId
            )

            homeViewModel.testInProgress(
                subjectId = subjectId,
                yearId = yearId
            ).observe(viewLifecycleOwner, Observer {
                when (it) {
                    true -> showQuestionInProgressDialog(subjectId = subjectId, yearId = yearId)
                    else -> navigateToQuestions(subjectId = subjectId, yearId = yearId)
                }
            })
        }

        val paddingTop = binding.textView.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(binding.textView) { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + paddingTop)
            insets
        }
    }

    private fun showQuestionInProgressDialog(subjectId: String, yearId: String) {
        AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.question_in_progress_dialog_message))
            .setPositiveButton(
                getString(R.string.question_in_progress_dialog_positive_button_text)
            ) { _, _ ->
                navigateToQuestions(subjectId = subjectId, yearId = yearId)
            }
            .setNegativeButton(
                getString(R.string.question_in_progress_dialog_negative_button_text)
            ) { _, _  ->
                clearUserAnswersForTest(subjectId = subjectId, yearId = yearId)
            }
            .setCancelable(true)
            .create()
            .show()
    }

    private fun clearUserAnswersForTest(subjectId: String, yearId: String) {
        homeViewModel.clearUserAnswersForTest(
            subjectId = subjectId,
            yearId = yearId
        ).observe(viewLifecycleOwner, Observer {
            if (it) navigateToQuestions(subjectId, yearId)
        })
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

        homeViewModel.getYears().observe(viewLifecycleOwner, Observer {
            it?.let { years: List<Year> ->
                mYears = years
                setupYearsAdapter(years)
            }
        })

        homeViewModel.getSubjectWithYears().observe(viewLifecycleOwner, Observer {
            it?.let { subjectWithYearsList: List<SubjectWithYears> ->
                val subjects = subjectWithYearsList.map {
                    val name = it.subject.name.capitalizeWords()
                    return@map it.subject.copy(name = name)
                }
                val years = subjectWithYearsList.map { it.years }

                mSubjects = subjects
                mYears = years.first()

                // set up the subjects spinner
                setupSubjectsAdapter(subjects)

                // set up the years spinner
                setupYearsAdapter(years.first())
            }
        })
    }

    private fun setupSubjectsAdapter(subjects: List<Subject>) {
        val lastSelectedSubjectId =
            mSharedPreferenceManager.getString(
                SharedPreferenceManagers.LAST_SELECTED_SUBJECT_ID,
                ""
            )

        // selected subjects
        val selectedSubject: Subject =
            subjects.firstOrNull { lastSelectedSubjectId == it.id } ?: subjects.first()

        mSelectedSubject = selectedSubject

        // set up the subjects spinner
        mSubjectAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, subjects)
        binding.selectSubjectAT.setAdapter(mSubjectAdapter)

        binding.selectSubjectAT.setText(when {
            subjects.size > 1 -> mSelectedSubject.name
            else -> ""
        }, false)
    }

    private fun setupYearsAdapter(years: List<Year>) {
        val lastSelectedYearId =
            mSharedPreferenceManager.getString(
                SharedPreferenceManagers.LAST_SELECTED_YEAR_ID,
                ""
            )

        // selected year
        val selectedYear =
            years.firstOrNull { lastSelectedYearId == it.id } ?: years.first()

        mSelectedYear = selectedYear

        // set up the years spinner
        mYearAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, years)
        binding.selectYearAT.setAdapter(mYearAdapter)

        binding.selectYearAT.setText(when {
            years.size > 1 -> selectedYear.toString()
            else -> ""
        }, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}