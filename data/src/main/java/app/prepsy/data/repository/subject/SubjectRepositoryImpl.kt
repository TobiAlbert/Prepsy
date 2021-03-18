package app.prepsy.data.repository.subject

import app.prepsy.data.mapper.Mapper
import app.prepsy.data.models.SubjectData
import app.prepsy.domain.entities.Subject
import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectLocalDataSource: SubjectLocalDataSource,
    private val mapper: Mapper<Subject, SubjectData>
) : SubjectRepository {

    override suspend fun getAllSubjects(): List<Subject> =
        subjectLocalDataSource.getAllSubjects().map { mapper.from(it) }

    override fun getYearsForSubject(subjectId: String): List<Int> = listOf(
        2010, 2011, 2012, 2013, 2014, 2015,
        2016, 2017, 2018, 2019, 2020
    )

}