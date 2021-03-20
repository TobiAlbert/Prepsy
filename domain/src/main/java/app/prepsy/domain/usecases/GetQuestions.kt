package app.prepsy.domain.usecases

import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestions @Inject constructor(private val repo: QuestionRepository) {

    suspend operator fun invoke(subjectId: String, yearId: String,): List<QuestionEntity> =
        repo.getQuestions(subjectId, yearId)
}