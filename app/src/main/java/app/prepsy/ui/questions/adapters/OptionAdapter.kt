package app.prepsy.ui.questions.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.prepsy.databinding.ListItemQuestionOptionsBinding
import app.prepsy.ui.models.Option

class OptionAdapter(
    private val options: List<Option>,
    private val onOptionSelected: (String) -> Unit
) : RecyclerView.Adapter<OptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val context = parent.context

        val inflater = LayoutInflater.from(context)
        val binding = ListItemQuestionOptionsBinding.inflate(inflater, parent, false)
        return OptionViewHolder(context, binding, onOptionSelected)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) = holder.bind(options[position])
}