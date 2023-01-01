package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.repository.QuestionRepository
import javax.inject.Inject

class IsTestInProgress @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Boolean = questionRepository.isTestInProgress(subjectId, yearId)
}