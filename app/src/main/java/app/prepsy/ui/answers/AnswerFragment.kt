package app.prepsy.ui.answers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.prepsy.R
import app.prepsy.databinding.FragmentAnswerBinding

class AnswerFragment : Fragment() {
    private var _binding: FragmentAnswerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        binding.answerRv.apply {
            val gridLayoutManger = GridLayoutManager(
                requireContext(),
                resources.getInteger(R.integer.grid_span_count), GridLayoutManager.VERTICAL,
                false
            )
            gridLayoutManger.paddingBottom

            adapter = AnswersAdapter()
            layoutManager = gridLayoutManger
            addItemDecoration(SpaceItemDecorator())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}