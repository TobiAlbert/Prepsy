package com.tobidaada.local.mapper

import app.prepsy.data.models.YearData
import com.tobidaada.local.models.YearLocal
import java.util.*
import javax.inject.Inject

class YearsLocalDataMapper @Inject constructor(): Mapper<YearData, YearLocal> {

    override fun from(e: YearLocal): YearData = YearData(id = e.id, name = e.name)

    override fun to(t: YearData): YearLocal =
        YearLocal(
            id = t.id,
            name = t.name,
            createdAt = Date(),
            updatedAt = Date()
        )
}