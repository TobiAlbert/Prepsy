package app.prepsy.domain.usecases

import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObservableQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(
        subjectId: String,
        yearId: String
    ): Flow<List<QuestionEntity>> = questionRepository.getObservableQuestions(subjectId, yearId)

}