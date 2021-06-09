package app.prepsy.ui.questions.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.args.QuestionPageMode
import app.prepsy.ui.questions.QuestionFragment

class QuestionPageAdapter(
    fragment: Fragment,
    private val questions: List<Question>,
    private val mode: QuestionPageMode
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = questions.size

    override fun createFragment(position: Int): Fragment =
        QuestionFragment.newInstance(questions[position], mode)
}