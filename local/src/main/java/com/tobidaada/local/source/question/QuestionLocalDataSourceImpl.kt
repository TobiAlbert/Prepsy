package com.tobidaada.local.source.question

import app.prepsy.data.models.OptionData
import app.prepsy.data.models.QuestionData
import app.prepsy.data.models.UserAnswerData
import app.prepsy.data.models.UserScoreData
import app.prepsy.data.repository.answer.AnswerLocalDataSource
import app.prepsy.data.repository.question.QuestionLocalDataSource
import com.tobidaada.local.dao.QuestionDao
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.models.OptionLocal
import com.tobidaada.local.models.QuestionAndOptions
import com.tobidaada.local.models.UserScoreLocal
import javax.inject.Inject

class QuestionLocalDataSourceImpl @Inject constructor(
    private val questionDao: QuestionDao,
    private val userAnswerLocalDataSource: AnswerLocalDataSource,
    private val optionsMapper: Mapper<OptionData, OptionLocal>,
    private val userScoreMapper: Mapper<UserScoreData, UserScoreLocal>
) : QuestionLocalDataSource {

    override suspend fun getQuestions(subjectId: String, yearId: String): List<QuestionData> {
        val questionsAndOptions = questionDao.getQuestionsBySubjectAndYear(subjectId, yearId)

        return questionsAndOptions.map { it: QuestionAndOptions ->
            val userOption: UserAnswerData? = userAnswerLocalDataSource.getUserAnswerByQuestionId(it.question.id)

            val answerId = it.question.rightOption
            val answer = it.options.first { answerId == it.id }
            val answerOption = OptionData(answer.id, answer.text, it.question.id)

            val options = it.options
                .filter { answerId != it.id }
                .map { optionsMapper.from(it) }.toMutableList()

            options.add(answerOption)
            options.shuffle()

            return@map QuestionData(
                id = it.question.id,
                text = it.question.text,
                userAnswerId = userOption?.optionId,
                answer = answerOption,
                options = options.toList()
            )
        }
    }

    override suspend fun getUserScore(subjectId: String, yearId: String): UserScoreData =
        userScoreMapper.from(questionDao.getUserScore(subjectId, yearId))
}