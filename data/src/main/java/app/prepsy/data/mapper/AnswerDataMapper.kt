package app.prepsy.data.mapper

import app.prepsy.data.models.Option
import javax.inject.Inject
import app.prepsy.domain.entities.Option as OptionEntity

class AnswerDataMapper @Inject constructor(): Mapper<OptionEntity, Option> {

    override fun from(e: Option): OptionEntity =
        OptionEntity(alphabet = e.alphabet, text = e.text)

    override fun to(t: OptionEntity): Option =
        Option(alphabet = t.alphabet, text = t.text)
}