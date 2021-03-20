package app.prepsy.domain.repository

import app.prepsy.domain.entities.QuestionEntity

interface QuestionRepository {
    suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity>
}