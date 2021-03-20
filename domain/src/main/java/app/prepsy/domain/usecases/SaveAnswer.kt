package app.prepsy.domain.usecases

import app.prepsy.domain.entities.OptionEntity
import app.prepsy.domain.repository.AnswerRepository
import javax.inject.Inject

class SaveAnswer @Inject constructor(private val repo: AnswerRepository) {
    operator fun invoke(
        option: OptionEntity,
        questionId: String
    ) = repo.saveAnswer(option, questionId)
}