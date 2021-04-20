package app.prepsy.domain.repository

import app.prepsy.domain.entities.UserAnswerEntity

interface AnswerRepository {
    suspend fun saveUserAnswer(entity: UserAnswerEntity)

    suspend fun getUserAnswerByQuestionId(questionId: String): UserAnswerEntity?
}