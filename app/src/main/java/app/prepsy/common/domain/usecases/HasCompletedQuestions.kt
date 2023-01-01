package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.repository.QuestionRepository
import javax.inject.Inject

class HasCompletedQuestions @Inject constructor(
    private val repo: QuestionRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Boolean = repo.hasCompletedQuestions(subjectId, yearId)
}