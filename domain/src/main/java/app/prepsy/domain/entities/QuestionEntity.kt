package app.prepsy.domain.entities

data class QuestionEntity(
    val text: String,
    val options: List<OptionEntity>,
    val answer: OptionEntity
)