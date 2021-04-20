package app.prepsy.domain.repository

import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.entities.UserScoreEntity

interface QuestionRepository {
    suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity>

    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreEntity
}