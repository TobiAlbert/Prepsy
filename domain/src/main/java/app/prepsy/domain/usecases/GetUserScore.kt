package app.prepsy.domain.usecases

import app.prepsy.domain.entities.UserScoreEntity
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class GetUserScore @Inject constructor(private val repo: QuestionRepository) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): UserScoreEntity = repo.getUserScore(subjectId, yearId)
}