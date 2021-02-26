package app.prepsy.data.mapper

import app.prepsy.data.models.Question
import app.prepsy.domain.entities.Question as QuestionEntity
import javax.inject.Inject

class QuestionDataMapper @Inject constructor(): Mapper<QuestionEntity, Question> {

    override fun from(e: Question): QuestionEntity =
        QuestionEntity(
            text = e.text,
            options = e.options,
            answer = e.answer,
        )

    override fun to(t: QuestionEntity): Question =
        Question(
            text = t.text,
            options = t.options,
            answer = t.answer,
        )
}