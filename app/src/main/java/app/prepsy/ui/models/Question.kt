package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val id: String,
    val text: String,
    val questionId: String
): Parcelable {
    override fun toString(): String = "Option($text)"
}

@Parcelize
data class Question(
    val id: String,
    val text: String,
    val userOptionId: String?,
    val options: List<Option>,
    val answer: Option
): Parcelable {
    override fun toString(): String = "Question($text)"
}