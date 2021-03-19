package app.prepsy.data.models

import app.prepsy.domain.entities.Option

data class QuestionData(
    val text: String,
    val options: List<Option>,
    val answer: Option
)