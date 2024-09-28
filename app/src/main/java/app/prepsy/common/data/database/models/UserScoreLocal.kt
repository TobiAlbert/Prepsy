package app.prepsy.common.data.database.models

import app.prepsy.common.domain.entities.UserScoreEntity

data class UserScoreLocal(
    val score: Int,
    val total: Int
) {
    fun toDomain(): UserScoreEntity =
        UserScoreEntity(
            score = score,
            total = total
        )
}