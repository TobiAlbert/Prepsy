package app.prepsy.data.models


data class QuestionData(
    val id: String,
    val text: String,
    val userAnswerId: String?,
    val options: List<OptionData>,
    val answer: OptionData
)