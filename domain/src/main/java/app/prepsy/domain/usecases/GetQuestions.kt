package app.prepsy.domain.usecases

import app.prepsy.domain.entities.Question
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestions @Inject constructor(private val repo: QuestionRepository) {

    operator fun invoke(subjectId: String, yearId: String,): List<Question> =
        repo.getQuestions(subjectId, yearId)
}