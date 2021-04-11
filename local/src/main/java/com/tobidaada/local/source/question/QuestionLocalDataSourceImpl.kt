package com.tobidaada.local.source.question

import app.prepsy.data.models.OptionData
import app.prepsy.data.models.QuestionData
import app.prepsy.data.repository.question.QuestionLocalDataSource
import com.tobidaada.local.dao.QuestionDao
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.models.OptionLocal
import com.tobidaada.local.models.QuestionAndOptions
import javax.inject.Inject

class QuestionLocalDataSourceImpl @Inject constructor(
    private val questionDao: QuestionDao,
    private val optionsMapper: Mapper<OptionData, OptionLocal>
) : QuestionLocalDataSource {

    override suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionData> {
        val questionsAndOptions = questionDao.getQuestionsBySubjectAndYear(subjectId, yearId)

        return questionsAndOptions.map { it: QuestionAndOptions ->
            val answerId = it.question.rightOption
            val answer = it.options.first { answerId == it.id }
            val answerOption = OptionData(answer.id, answer.text, it.question.id)

            val options = it.options
                .filter { answerId != it.id }
                .map { optionsMapper.from(it) }.toMutableList()

            options.add(answerOption)
            options.shuffle()

            return@map QuestionData(
                text = it.question.text,
                answer = answerOption,
                options = options.toList()
            )
        }
    }
}