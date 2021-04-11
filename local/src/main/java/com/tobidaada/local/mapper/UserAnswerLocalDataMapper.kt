package com.tobidaada.local.mapper

import app.prepsy.data.models.UserAnswerData
import com.tobidaada.local.models.UserAnswerLocal
import java.util.*
import javax.inject.Inject

class UserAnswerLocalDataMapper @Inject constructor(): Mapper<UserAnswerData, UserAnswerLocal> {

    override fun from(e: UserAnswerLocal): UserAnswerData =
        UserAnswerData(e.questionId, e.optionId)

    override fun to(t: UserAnswerData): UserAnswerLocal =
        UserAnswerLocal(
            questionId = t.questionId,
            optionId = t.optionId,
            createdAt = Date(),
            updatedAt = Date()
        )
}