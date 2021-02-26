package app.prepsy.domain.repository

import app.prepsy.domain.entities.Option

interface AnswerRepository {
    fun saveAnswer(option: Option, questionId: String)
}