package app.prepsy.domain.usecases

import app.prepsy.domain.entities.UserAnswerEntity
import app.prepsy.domain.repository.AnswerRepository
import javax.inject.Inject

class SaveAnswer @Inject constructor(private val repo: AnswerRepository) {
    suspend operator fun invoke(
        questionId: String,
        optionId: String
    ) = repo.saveUserAnswer(UserAnswerEntity(questionId, optionId))
}