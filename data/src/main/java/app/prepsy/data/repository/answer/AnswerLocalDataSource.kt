package app.prepsy.data.repository.answer

import app.prepsy.data.models.OptionData

interface AnswerLocalDataSource {
    fun saveAnswer(questionId: String, option: OptionData)
}