package app.prepsy.data.mapper

import app.prepsy.data.models.UserAnswerData
import app.prepsy.domain.entities.UserAnswerEntity
import javax.inject.Inject

class UserAnswerDataMapper @Inject constructor() : Mapper<UserAnswerEntity, UserAnswerData> {

    override fun from(e: UserAnswerData): UserAnswerEntity =
        UserAnswerEntity(e.questionId, e.optionId)

    override fun to(t: UserAnswerEntity): UserAnswerData =
        UserAnswerData(t.questionId, t.optionId)
}