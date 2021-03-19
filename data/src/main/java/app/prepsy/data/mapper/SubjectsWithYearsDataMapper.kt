package app.prepsy.data.mapper

import app.prepsy.data.models.SubjectData
import app.prepsy.data.models.SubjectWithYearsData
import app.prepsy.data.models.YearData
import app.prepsy.domain.entities.SubjectEntity
import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.entities.YearEntity
import javax.inject.Inject

class SubjectsWithYearsDataMapper @Inject constructor(
    private val subjectsMapper: Mapper<SubjectEntity, SubjectData>,
    private val yearMapper: Mapper<YearEntity, YearData>
): Mapper<SubjectWithYearsEntity, SubjectWithYearsData> {

    override fun from(e: SubjectWithYearsData): SubjectWithYearsEntity {
        val years = e.years.map { yearMapper.from(it) }
        val subject = subjectsMapper.from(e.subject)
        return SubjectWithYearsEntity(subject = subject, years = years)
    }

    override fun to(t: SubjectWithYearsEntity): SubjectWithYearsData {
        val years = t.years.map { yearMapper.to(it) }
        val subject = subjectsMapper.to(t.subject)
        return SubjectWithYearsData(subject = subject, years = years)
    }
}