package app.prepsy.domain.usecases

import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class GetIsTestInProgressUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Boolean = questionRepository.isTestInProgress(subjectId, yearId)
}