package app.prepsy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.prepsy.R
import app.prepsy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        val years = listOf(
            2010, 2011, 2012, 2013, 2014, 2015,
            2016, 2017, 2018, 2019, 2020
        )
        val yearsAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, years)
        binding.selectYearAT.apply {
            setAdapter(yearsAdapter)
            setText(years.first().toString(), false)
        }

        val subjects = listOf(
            "Agriculture",
            "Arabic",
            "Art",
            "Biology",
            "Chemistry",
            "CRS",
            "Commerce",
            "Economics",
            "French",
            "Geography",
            "Government",
            "Hausa",
            "History",
            "Home Economics",
            "Igbo",
            "Islamic Studies",
            "Literature in English",
            "Mathematics",
            "Music",
            "Physics",
            "Principles of Accounts",
            "Use of English",
            "Yoruba"
        )

        val subjectsAdapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown, subjects)
        binding.selectSubjectAT.apply {
            setAdapter(subjectsAdapter)
            setText(subjects.first(), false)
        }

        binding.viewQuestionsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionPageFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}