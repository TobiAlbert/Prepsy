package app.prepsy.ui.questions.adapters

import androidx.recyclerview.widget.RecyclerView
import app.prepsy.databinding.ListItemQuestionOptionsBinding
import app.prepsy.ui.models.Option

class OptionViewHolder(
    private val binding: ListItemQuestionOptionsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(option: Option) = with(binding) {
        questionOptionAlphabet.text = option.alphabet
        questionOptionText.text = option.text
        Unit
    }
}