package app.prepsy.common.data.repository

import app.prepsy.common.data.database.dao.QuestionDao
import app.prepsy.common.data.database.models.QuestionOptionsUserAnswer
import app.prepsy.common.domain.entities.QuestionEntity
import app.prepsy.common.domain.entities.UserScoreEntity
import app.prepsy.common.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UtmeQuestionRepository @Inject constructor(
    private val questionDao: QuestionDao,
) : QuestionRepository {
    override suspend fun getQuestions(
        subjectId: String,
        yearId: String,
    ): List<QuestionEntity> =
        questionDao.getQuestions(
            subjectId = subjectId,
            yearId = yearId
        ).map(QuestionOptionsUserAnswer::toDomain)

    override suspend fun getUserScore(
        subjectId: String,
        yearId: String,
    ): UserScoreEntity =
        questionDao.getUserScore(
            subjectId = subjectId,
            yearId = yearId,
        ).toDomain()

    override suspend fun hasCompletedQuestions(
        subjectId: String,
        yearId: String
    ): Boolean =
        questionDao.hasCompletedQuestions(
            subjectId = subjectId,
            yearId = yearId,
        ).toBoolean()

    override fun getObservableQuestions(
        subjectId: String,
        yearId: String,
    ): Flow<List<QuestionEntity>> =
        questionDao.getObservableQuestions(
            subjectId = subjectId,
            yearId = yearId
        ).map { it.map(QuestionOptionsUserAnswer::toDomain) }

    override suspend fun isTestInProgress(
        subjectId: String,
        yearId: String,
    ): Boolean =
        questionDao.hasCompletedQuestions(
            subjectId = subjectId,
            yearId = yearId,
        ).toBoolean().not()

    private fun Int.toBoolean(): Boolean = this > 0
}