package app.prepsy.ui.answers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.prepsy.R

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(text: String) {
        itemView.findViewById<TextView>(R.id.answerText).text = text
    }
}