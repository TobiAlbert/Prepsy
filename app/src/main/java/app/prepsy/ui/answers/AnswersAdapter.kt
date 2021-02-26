package app.prepsy.ui.answers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.prepsy.databinding.ListItemAnswerSheetBinding

class AnswersAdapter : RecyclerView.Adapter<AnswerViewHolder>() {
    private val answers = listOf("1.   A", "2.   B", "3.   C", "4.   D", "5.   E", "6.   F")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnswerViewHolder(ListItemAnswerSheetBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = answers.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) = holder.bind(answers[position])

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }
    }
}