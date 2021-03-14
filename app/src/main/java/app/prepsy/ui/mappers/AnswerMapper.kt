package app.prepsy.ui.mappers

import app.prepsy.ui.models.Option
import app.prepsy.domain.entities.Option as OptionEntity
import javax.inject.Inject

class AnswerMapper @Inject constructor(): Mapper<Option, OptionEntity> {

    override fun from(e: OptionEntity): Option =
        Option(
            alphabet = e.alphabet,
            text = e.text,
        )

    override fun to(t: Option): OptionEntity =
        OptionEntity(
            alphabet = t.alphabet,
            text = t.text,
        )
}