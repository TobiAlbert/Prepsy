package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.repository.QuestionRepository
import app.prepsy.utils.AppDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsTestInProgress @Inject constructor(
    private val repo: QuestionRepository,
    private val appDispatcher: AppDispatcher,
) {
    suspend operator fun invoke(
        subjectId: String,
        yearId: String
    ): Boolean = withContext(appDispatcher.io) {
        val questionSize = repo.getQuestions(subjectId, yearId).size

        if (questionSize == 0) {
            return@withContext false
        }

        val numberOfAttemptedQuestions = repo.getUserAnswersCount(subjectId, yearId)

        return@withContext numberOfAttemptedQuestions in 1 until questionSize
    }
}