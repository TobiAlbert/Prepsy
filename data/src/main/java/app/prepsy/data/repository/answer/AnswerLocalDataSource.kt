package app.prepsy.data.repository.answer

import app.prepsy.data.models.UserAnswerData

interface AnswerLocalDataSource {
    suspend fun saveAnswer(data: UserAnswerData)

    suspend fun getUserAnswerByQuestionId(questionId: String): UserAnswerData?
}