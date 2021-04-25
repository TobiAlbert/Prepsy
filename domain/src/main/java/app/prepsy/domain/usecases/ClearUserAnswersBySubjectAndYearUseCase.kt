package app.prepsy.domain.usecases

import app.prepsy.domain.repository.AnswerRepository
import javax.inject.Inject

class ClearUserAnswersBySubjectAndYearUseCase @Inject constructor(
    private val answerRepository: AnswerRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Unit = answerRepository.clearUserAnswersBySubjectAndYearId(subjectId, yearId)
}