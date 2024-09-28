package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.entities.UserAnswerEntity
import app.prepsy.common.domain.repository.AnswerRepository
import javax.inject.Inject

class SaveAnswer @Inject constructor(private val repo: AnswerRepository) {
    suspend operator fun invoke(
        questionId: String,
        optionId: String
    ) = repo.saveUserAnswer(UserAnswerEntity(questionId, optionId))
}