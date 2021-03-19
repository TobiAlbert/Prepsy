package com.tobidaada.local.mapper

import app.prepsy.data.models.SubjectData
import com.tobidaada.local.models.SubjectLocal
import java.util.*
import javax.inject.Inject

class SubjectLocalDataMapper @Inject constructor(): Mapper<SubjectData, SubjectLocal> {

    override fun from(e: SubjectLocal): SubjectData =
        SubjectData(
            id = e.id,
            name = e.name
        )

    override fun to(t: SubjectData): SubjectLocal =
        SubjectLocal(
            id = t.id,
            name = t.name,
            slug = "",
            createdAt = Date(),
            updatedAt = Date()
        )

}