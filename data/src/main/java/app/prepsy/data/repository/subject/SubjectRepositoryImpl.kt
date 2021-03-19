package app.prepsy.data.repository.subject

import app.prepsy.data.mapper.Mapper
import app.prepsy.data.models.SubjectData
import app.prepsy.data.models.SubjectWithYearsData
import app.prepsy.domain.entities.SubjectEntity
import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectLocalDataSource: SubjectLocalDataSource,
    private val subjectsMapper: Mapper<SubjectEntity, SubjectData>,
    private val subjectsWithYearsMapper: Mapper<SubjectWithYearsEntity, SubjectWithYearsData>
) : SubjectRepository {

    override suspend fun getAllSubjects(): List<SubjectEntity> =
        subjectLocalDataSource.getAllSubjects().map { subjectsMapper.from(it) }

    override suspend fun getYearsForSubject(): List<SubjectWithYearsEntity> =
        subjectLocalDataSource.getSubjectsWithYears().map {
            subjectsWithYearsMapper.from(it)
        }
}