package app.prepsy.domain.usecases

import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class HasCompleteQuestionUseCase @Inject constructor(
    private val repo: QuestionRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Boolean = repo.hasCompletedQuestions(subjectId, yearId)
}