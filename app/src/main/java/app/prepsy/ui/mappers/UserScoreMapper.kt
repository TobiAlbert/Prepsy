package app.prepsy.ui.mappers

import app.prepsy.domain.entities.UserScoreEntity
import app.prepsy.ui.models.UserScore
import javax.inject.Inject

class UserScoreMapper @Inject constructor(): Mapper<UserScore, UserScoreEntity> {
    override fun from(e: UserScoreEntity): UserScore =
        UserScore(score = e.score, total = e.total)

    override fun to(t: UserScore): UserScoreEntity =
        UserScoreEntity(score = t.score, total = t.total)
}