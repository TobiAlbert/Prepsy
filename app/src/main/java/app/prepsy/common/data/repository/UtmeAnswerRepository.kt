package app.prepsy.common.data.repository

import app.prepsy.common.data.database.dao.UserAnswerDao
import app.prepsy.common.data.database.models.UserAnswerLocal
import app.prepsy.common.domain.entities.UserAnswerEntity
import app.prepsy.common.domain.repository.AnswerRepository
import javax.inject.Inject

class UtmeAnswerRepository @Inject constructor(
    private val answerDao: UserAnswerDao,
) : AnswerRepository {

    override suspend fun saveUserAnswer(entity: UserAnswerEntity) =
        answerDao.add(UserAnswerLocal.fromDomain(entity))

    override suspend fun getUserAnswer(questionId: String): UserAnswerEntity? =
        answerDao.getByQuestionId(questionId)?.toDomain()

    override suspend fun clearAnswersForSubject(subjectId: String, yearId: String) =
        answerDao.clearAnswersForSubject(
            subjectId = subjectId,
            yearId = yearId,
        )
}