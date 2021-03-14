package app.prepsy.data.repository.answer

import app.prepsy.domain.entities.Option
import app.prepsy.domain.repository.AnswerRepository
import javax.inject.Inject

class AnswerRepositoryImpl @Inject constructor(): AnswerRepository {
    override fun saveAnswer(option: Option, questionId: String) {

    }
}