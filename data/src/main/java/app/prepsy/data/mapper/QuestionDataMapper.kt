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
            id = e.id,
            text = e.text,
            userOptionId = e.userAnswerId,
            options = e.options.map { optionMapper.from(it) },
            answer = optionMapper.from(e.answer),
        )

    override fun to(t: QuestionEntity): QuestionData =
        QuestionData(
            id = t.id,
            text = t.text,
            userAnswerId = t.userOptionId,
            options = t.options.map { optionMapper.to(it) },
            answer = optionMapper.to(t.answer),
        )
}