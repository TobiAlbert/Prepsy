package app.prepsy.ui.mappers

import app.prepsy.ui.models.Option
import app.prepsy.domain.entities.OptionEntity as OptionEntity
import app.prepsy.ui.models.Question
import app.prepsy.domain.entities.QuestionEntity as QuestionEntity
import javax.inject.Inject

class QuestionMapper @Inject constructor(
    private val answerMapper: Mapper<Option, OptionEntity>
): Mapper<Question, QuestionEntity> {

    override fun from(e: QuestionEntity): Question =
        Question(
            text = e.text,
            options = e.options.map { answerMapper.from(it) },
            answer = answerMapper.from(e.answer),
        )

    override fun to(t: Question): QuestionEntity =
        QuestionEntity(
            text = t.text,
            options = t.options.map { answerMapper.to(it) },
            answer = answerMapper.to(t.answer),
        )
}