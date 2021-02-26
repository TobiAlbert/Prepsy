package app.prepsy.ui.models

import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val alphabet: String,
    val text: String
)

@Parcelize
data class Question(
    val text: String,
    val options: List<Option>,
    val answer: Option
)