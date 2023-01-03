package app.prepsy.common.domain.repository

import app.prepsy.common.domain.entities.QuestionEntity
import app.prepsy.common.domain.entities.UserScoreEntity
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity>

    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreEntity

    suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Boolean

    fun getObservableQuestions(subjectId: String, yearId: String): Flow<List<QuestionEntity>>

    suspend fun getUserAnswersCount(subjectId: String, yearId: String): Int
}