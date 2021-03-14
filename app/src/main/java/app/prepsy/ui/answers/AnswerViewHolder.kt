package app.prepsy.ui.answers

import androidx.recyclerview.widget.RecyclerView
import app.prepsy.databinding.ListItemAnswerSheetBinding

class AnswerViewHolder(private val binding: ListItemAnswerSheetBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.answerText.text = text
    }
}