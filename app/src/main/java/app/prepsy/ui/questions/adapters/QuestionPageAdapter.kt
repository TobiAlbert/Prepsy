package app.prepsy.ui.questions.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.prepsy.ui.models.Question
import app.prepsy.ui.questions.QuestionFragment

class QuestionPageAdapter(fragment: Fragment, private val questions: List<Question>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = questions.size

    override fun createFragment(position: Int): Fragment {
        Log.i("QuestionPageAdapter", "Creating fragment at position: $position")
        return QuestionFragment.newInstance(questions[position])
    }

}