package app.prepsy.data.mapper

import app.prepsy.data.models.OptionData
import javax.inject.Inject
import app.prepsy.domain.entities.OptionEntity as OptionEntity

class AnswerDataMapper @Inject constructor(): Mapper<OptionEntity, OptionData> {

    override fun from(e: OptionData): OptionEntity =
        OptionEntity(
            id = e.id,
            text = e.text,
            questionId = e.questionId
        )

    override fun to(t: OptionEntity): OptionData =
        OptionData(
            id = t.id,
            text = t.text,
            questionId = t.questionId
        )
}