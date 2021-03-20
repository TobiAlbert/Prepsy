package app.prepsy.data.models


data class QuestionData(
    val text: String,
    val options: List<OptionData>,
    val answer: OptionData
)