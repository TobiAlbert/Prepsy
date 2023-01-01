package app.prepsy.common.domain.repository

import app.prepsy.common.domain.entities.UserAnswerEntity

interface AnswerRepository {
    suspend fun saveUserAnswer(entity: UserAnswerEntity)

    suspend fun getUserAnswer(questionId: String): UserAnswerEntity?

    suspend fun clearAnswersForSubject(subjectId: String, yearId: String)
}