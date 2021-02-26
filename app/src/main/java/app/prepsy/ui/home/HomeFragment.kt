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
import app.prepsy.utils.createSubjectsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mSubjects: List<Subject>
    private lateinit var mSubjectAdapter: ArrayAdapter<Subject>
    private lateinit var mYearAdapter: ArrayAdapter<String>

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

        mSubjectAdapter = requireContext().createSubjectsAdapter(mutableListOf())
        mYearAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, mutableListOf())

        binding.selectYearAT.setAdapter(mYearAdapter)

        binding.selectSubjectAT.setAdapter(mSubjectAdapter)
        binding.selectSubjectAT.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (::mSubjects.isInitialized) {
                    val subject: Subject = mSubjects[position]
                    homeViewModel.getAvailableYearsForSubject(subject.id)
                }
            }

        binding.viewQuestionsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionPageFragment)
        }

        val paddingTop = binding.textView.paddingTop
        binding.textView.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + paddingTop)
            insets
        }
    }

    private fun observeViewModel() {
        homeViewModel.getSubjects().observe(viewLifecycleOwner, Observer {
            it?.let { subjects: List<Subject> ->
                mSubjects = subjects

                mSubjectAdapter.clear()
                mSubjectAdapter.addAll(subjects)
                mSubjectAdapter.notifyDataSetChanged()
                binding.selectSubjectAT.setText(subjects.first().name, false)
            }
        })

        homeViewModel.getYears().observe(viewLifecycleOwner, Observer {
            it?.let { years: List<String> ->

                mYearAdapter.clear()
                mYearAdapter.addAll(years)

                binding.selectYearAT.setText(years.first().toString(), false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}