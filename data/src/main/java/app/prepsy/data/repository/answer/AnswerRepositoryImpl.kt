package app.prepsy.data.repository.answer

import app.prepsy.data.mapper.Mapper
import app.prepsy.data.models.UserAnswerData
import app.prepsy.domain.entities.UserAnswerEntity
import app.prepsy.domain.repository.AnswerRepository
import javax.inject.Inject

class AnswerRepositoryImpl @Inject constructor(
    private val localDataSource: AnswerLocalDataSource,
    private val userDataMapper: Mapper<UserAnswerEntity, UserAnswerData>
) : AnswerRepository {

    override suspend fun saveUserAnswer(entity: UserAnswerEntity) {
        localDataSource.saveAnswer(userDataMapper.to(entity))
    }

    override suspend fun getUserAnswerByQuestionId(questionId: String): UserAnswerEntity? {
        return localDataSource.getUserAnswerByQuestionId(questionId)?.let { userDataMapper.from(it) }
    }
}