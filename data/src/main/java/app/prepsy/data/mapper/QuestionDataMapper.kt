package app.prepsy.data.mapper

import app.prepsy.data.models.OptionData
import app.prepsy.data.models.QuestionData
import app.prepsy.domain.entities.OptionEntity
import app.prepsy.domain.entities.QuestionEntity as QuestionEntity
import javax.inject.Inject

class QuestionDataMapper @Inject constructor(
    private val optionMapper: Mapper<OptionEntity, OptionData>
): Mapper<QuestionEntity, QuestionData> {

    override fun from(e: QuestionData): QuestionEntity =
        QuestionEntity(
            text = e.text,
            options = e.options.map { optionMapper.from(it) },
            answer = optionMapper.from(e.answer),
        )

    override fun to(t: QuestionEntity): QuestionData =
        QuestionData(
            text = t.text,
            options = t.options.map { optionMapper.to(it) },
            answer = optionMapper.to(t.answer),
        )
}