package app.prepsy.domain.repository

import app.prepsy.domain.entities.Question

interface QuestionRepository {
    fun getQuestions(subjectId: String, yearId: String): List<Question>
}