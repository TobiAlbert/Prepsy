package app.prepsy.domain.entities

data class Question(
    val text: String,
    val options: List<Option>,
    val answer: Option
)