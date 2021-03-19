package app.prepsy.data.mapper

import app.prepsy.data.models.OptionData
import javax.inject.Inject
import app.prepsy.domain.entities.Option as OptionEntity

class AnswerDataMapper @Inject constructor(): Mapper<OptionEntity, OptionData> {

    override fun from(e: OptionData): OptionEntity =
        OptionEntity(alphabet = e.alphabet, text = e.text)

    override fun to(t: OptionEntity): OptionData =
        OptionData(alphabet = t.alphabet, text = t.text)
}