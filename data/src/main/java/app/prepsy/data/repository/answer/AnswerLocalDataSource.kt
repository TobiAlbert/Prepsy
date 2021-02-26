package app.prepsy.data.repository.answer

import app.prepsy.data.models.Option

interface AnswerLocalDataSource {
    fun saveAnswer(questionId: String, option: Option)
}