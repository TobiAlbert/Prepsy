package com.tobidaada.local.mapper

import app.prepsy.data.models.OptionData
import com.tobidaada.local.models.OptionLocal
import java.util.*
import javax.inject.Inject

class OptionsLocalDataMapper @Inject constructor(): Mapper<OptionData, OptionLocal> {

    override fun from(e: OptionLocal): OptionData =
        OptionData(
            id = e.id,
            text = e.text,
            questionId = e.questionId,
        )

    override fun to(t: OptionData): OptionLocal =
        OptionLocal(
            id = t.id,
            text = t.text,
            questionId = t.questionId,
            createdAt = Date(),
            updatedAt = Date()
        )
}