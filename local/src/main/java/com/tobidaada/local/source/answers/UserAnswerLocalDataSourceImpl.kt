package com.tobidaada.local.source.answers

import app.prepsy.data.models.UserAnswerData
import app.prepsy.data.repository.answer.AnswerLocalDataSource
import com.tobidaada.local.dao.UserAnswerDao
import com.tobidaada.local.mapper.UserAnswerLocalDataMapper
import javax.inject.Inject

class UserAnswerLocalDataSourceImpl @Inject constructor(
    private val userAnswerDao: UserAnswerDao,
    private val userAnswerLocalDataMapper: UserAnswerLocalDataMapper
) : AnswerLocalDataSource {

    override suspend fun saveAnswer(data: UserAnswerData) =
        userAnswerDao.add(userAnswerLocalDataMapper.to(data))

    override suspend fun getUserAnswerByQuestionId(questionId: String): UserAnswerData? =
        userAnswerDao.getByQuestionId(questionId)?.let { userAnswerLocalDataMapper.from(it) }
}