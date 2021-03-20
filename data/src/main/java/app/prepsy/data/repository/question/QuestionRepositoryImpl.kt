package app.prepsy.data.repository.question

import app.prepsy.data.mapper.Mapper
import app.prepsy.data.models.QuestionData
import app.prepsy.domain.entities.OptionEntity
import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val localDataSource: QuestionLocalDataSource,
    private val questionMapper: Mapper<QuestionEntity, QuestionData>
): QuestionRepository {

    override suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionEntity> =
        localDataSource.getQuestions(subjectId, yearId).map { questionMapper.from(it) }
}