package app.prepsy.data.mapper

import app.prepsy.data.models.SubjectData
import app.prepsy.domain.entities.SubjectEntity as SubjectEntity
import javax.inject.Inject

class SubjectDataMapper @Inject constructor(): Mapper<SubjectEntity, SubjectData> {
    override fun from(e: SubjectData): SubjectEntity = SubjectEntity(
        id = e.id,
        name = e.name
    )

    override fun to(t: SubjectEntity): SubjectData = SubjectData(
        id = t.id,
        name = t.name
    )
}