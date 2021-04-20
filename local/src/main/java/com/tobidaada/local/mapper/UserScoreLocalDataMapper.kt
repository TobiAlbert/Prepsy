package com.tobidaada.local.mapper

import app.prepsy.data.models.UserScoreData
import com.tobidaada.local.models.UserScoreLocal
import javax.inject.Inject

class UserScoreLocalDataMapper @Inject constructor(): Mapper<UserScoreData, UserScoreLocal> {
    override fun from(e: UserScoreLocal): UserScoreData =
        UserScoreData(score = e.score, total = e.total)

    override fun to(t: UserScoreData): UserScoreLocal =
        UserScoreLocal(score = t.score, total = t.total)
}