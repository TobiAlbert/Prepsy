package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val id: String,
    val text: String,
    val questionId: String
): Parcelable

@Parcelize
data class Question(
    val text: String,
    val options: List<Option>,
    val answer: Option
): Parcelable