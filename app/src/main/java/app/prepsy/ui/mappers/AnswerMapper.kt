package app.prepsy.ui.mappers

import app.prepsy.ui.models.Option
import app.prepsy.domain.entities.OptionEntity as OptionEntity
import javax.inject.Inject

class AnswerMapper @Inject constructor(): Mapper<Option, OptionEntity> {

    override fun from(e: OptionEntity): Option =
        Option(
            id = e.id,
            text = e.text,
            questionId = e.questionId
        )

    override fun to(t: Option): OptionEntity =
        OptionEntity(
            id = t.id,
            text = t.text,
            questionId = t.questionId
        )
}