package app.prepsy.domain.entities

data class QuestionEntity(
    val id: String,
    val text: String,
    val userOptionId: String?,
    val options: List<OptionEntity>,
    val answer: OptionEntity
)