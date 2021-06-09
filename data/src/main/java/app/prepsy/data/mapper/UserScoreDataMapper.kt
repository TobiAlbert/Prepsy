package app.prepsy.data.mapper

import app.prepsy.data.models.UserScoreData
import app.prepsy.domain.entities.UserScoreEntity
import javax.inject.Inject

class UserScoreDataMapper @Inject constructor(): Mapper<UserScoreEntity, UserScoreData> {
    override fun from(e: UserScoreData): UserScoreEntity =
        UserScoreEntity(score = e.score, total = e.total)

    override fun to(t: UserScoreEntity): UserScoreData =
        UserScoreData(score = t.score, total = t.total)
}