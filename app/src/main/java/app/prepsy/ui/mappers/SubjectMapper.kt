package app.prepsy.ui.mappers

import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.ui.models.Subject
import javax.inject.Inject

class SubjectMapper @Inject constructor(): Mapper<Subject, SubjectEntity> {
    override fun from(e: SubjectEntity): Subject = Subject(
        id = e.id,
        name = e.name
    )

    override fun to(t: Subject): SubjectEntity = SubjectEntity(
        id = t.id,
        name = t.name
    )
}