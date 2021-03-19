package com.tobidaada.local.mapper

import app.prepsy.data.models.SubjectData
import app.prepsy.data.models.SubjectWithYearsData
import app.prepsy.data.models.YearData
import com.tobidaada.local.models.SubjectLocal
import com.tobidaada.local.models.SubjectWithYearsLocal
import com.tobidaada.local.models.YearLocal
import javax.inject.Inject

class SubjectWithYearsLocalDataMapper @Inject constructor(
    private val subjectsMapper: Mapper<SubjectData, SubjectLocal>,
    private val yearMapper: Mapper<YearData, YearLocal>
): Mapper<SubjectWithYearsData, SubjectWithYearsLocal> {

    override fun from(e: SubjectWithYearsLocal): SubjectWithYearsData {
        val subject = subjectsMapper.from(e.subject)
        val years = e.years.map { yearMapper.from(it) }
        return SubjectWithYearsData(subject = subject, years = years)
    }

    override fun to(t: SubjectWithYearsData): SubjectWithYearsLocal {
        val subject = subjectsMapper.to(t.subject)
        val years = t.years.map { yearMapper.to(it) }
        return SubjectWithYearsLocal(subject = subject, years = years)
    }
}