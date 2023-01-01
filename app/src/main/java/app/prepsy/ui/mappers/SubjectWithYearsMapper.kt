package app.prepsy.ui.mappers

import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.entities.YearEntity
import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.SubjectWithYears
import app.prepsy.ui.models.Year
import javax.inject.Inject

class SubjectWithYearsMapper @Inject constructor(
    private val subjectMapper: Mapper<Subject, SubjectEntity>,
    private val yearMapper: Mapper<Year, YearEntity>
): Mapper<SubjectWithYears, SubjectWithYearsEntity> {

    override fun from(e: SubjectWithYearsEntity): SubjectWithYears {
        val subject = subjectMapper.from(e.subject)
        val years = e.years.map { yearMapper.from(it) }
        return SubjectWithYears(subject = subject, years = years)
    }

    override fun to(t: SubjectWithYears): SubjectWithYearsEntity {
        val subject = subjectMapper.to(t.subject)
        val years = t.years.map { yearMapper.to(it) }
        return SubjectWithYearsEntity(subject = subject, years = years)
    }

}