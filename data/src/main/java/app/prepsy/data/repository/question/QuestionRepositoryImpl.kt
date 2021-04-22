package app.prepsy.data.repository.question

import app.prepsy.data.mapper.Mapper
import app.prepsy.data.models.QuestionData
import app.prepsy.data.models.UserScoreData
import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.entities.UserScoreEntity
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val localDataSource: QuestionLocalDataSource,
    private val questionMapper: Mapper<QuestionEntity, QuestionData>,
    private val userScoreMapper: Mapper<UserScoreEntity, UserScoreData>
): QuestionRepository {

    override suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity> =
        localDataSource.getQuestions(subjectId, yearId).map { questionMapper.from(it) }

    override suspend fun getUserScore(subjectId: String, yearId: String): UserScoreEntity =
        userScoreMapper.from(localDataSource.getUserScore(subjectId, yearId))

    override suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Boolean =
        localDataSource.hasCompletedQuestions(subjectId, yearId)
}