package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.entities.UserScoreEntity
import app.prepsy.common.domain.repository.QuestionRepository
import javax.inject.Inject

class GetUserScore @Inject constructor(private val repo: QuestionRepository) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): UserScoreEntity = repo.getUserScore(subjectId, yearId)
}