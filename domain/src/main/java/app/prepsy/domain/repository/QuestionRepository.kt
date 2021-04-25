package app.prepsy.domain.repository

import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.entities.UserScoreEntity
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity>

    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreEntity

    suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Boolean

    fun getObservableQuestions(subjectId: String, yearId: String): Flow<List<QuestionEntity>>
}