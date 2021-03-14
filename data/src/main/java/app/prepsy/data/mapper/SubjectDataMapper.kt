package app.prepsy.data.mapper

import app.prepsy.data.models.Subject
import app.prepsy.domain.entities.Subject as SubjectEntity
import javax.inject.Inject

class SubjectDataMapper @Inject constructor(): Mapper<SubjectEntity, Subject> {
    override fun from(e: Subject): SubjectEntity = SubjectEntity(
        id = e.id,
        name = e.name
    )

    override fun to(t: SubjectEntity): Subject = Subject(
        id = t.id,
        name = t.name
    )
}