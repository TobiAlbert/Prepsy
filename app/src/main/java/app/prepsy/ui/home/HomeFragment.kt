package app.prepsy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentHomeBinding
import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.SubjectIdYearId
import app.prepsy.ui.models.SubjectWithYears
import app.prepsy.ui.models.Year
import app.prepsy.utils.capitalizeWords
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

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
    ): View?  {
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
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (::mSubjects.isInitialized) {
                    val subject: Subject = mSubjects[position]
                    mSelectedSubject = subject
                    homeViewModel.getAvailableYearsForSubject(subject.id)
                }
            }

        binding.viewQuestionsBtn.setOnClickListener {
            // ensure there are loaded subjects
            if (!::mSubjects.isInitialized) return@setOnClickListener
            if (!::mYears.isInitialized) return@setOnClickListener

            // get current subject and year id
            val subjectId = mSelectedSubject.id
            val yearId = mSelectedYear.id

            val action =
                HomeFragmentDirections.actionHomeFragmentToQuestionPageFragment(SubjectIdYearId(subjectId, yearId))

            findNavController().navigate(action)
        }

        val paddingTop = binding.textView.paddingTop
        binding.textView.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + paddingTop)
            insets
        }
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
        // selected subjects
        mSelectedSubject = subjects.first()

        // set up the subjects spinner
        mSubjectAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, subjects)
        binding.selectSubjectAT.setAdapter(mSubjectAdapter)

        binding.selectSubjectAT.setText(when {
            subjects.size > 1 -> subjects.first().name
            else -> ""
        }, false)
    }

    private fun setupYearsAdapter(years: List<Year>) {
        // selected year
        mSelectedYear = years.first()

        // set up the years spinner
        mYearAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, years)
        binding.selectYearAT.setAdapter(mYearAdapter)

        binding.selectYearAT.setText(when {
            years.size > 1 -> years.first().toString()
            else -> ""
        }, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}