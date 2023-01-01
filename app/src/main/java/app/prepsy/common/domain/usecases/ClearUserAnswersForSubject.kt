package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.repository.AnswerRepository
import javax.inject.Inject

class ClearUserAnswersForSubject @Inject constructor(
    private val answerRepository: AnswerRepository
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String,
    ): Unit =
        answerRepository.clearAnswersForSubject(
            subjectId = subjectId,
            yearId = yearId,
        )
}