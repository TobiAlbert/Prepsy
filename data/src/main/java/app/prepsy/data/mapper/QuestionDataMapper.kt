package app.prepsy.data.mapper

import app.prepsy.data.models.QuestionData
import app.prepsy.domain.entities.Question as QuestionEntity
import javax.inject.Inject

class QuestionDataMapper @Inject constructor(): Mapper<QuestionEntity, QuestionData> {

    override fun from(e: QuestionData): QuestionEntity =
        QuestionEntity(
            text = e.text,
            options = e.options,
            answer = e.answer,
        )

    override fun to(t: QuestionEntity): QuestionData =
        QuestionData(
            text = t.text,
            options = t.options,
            answer = t.answer,
        )
}