package app.prepsy.domain.repository

import app.prepsy.domain.entities.OptionEntity

interface AnswerRepository {
    fun saveAnswer(option: OptionEntity, questionId: String)
}