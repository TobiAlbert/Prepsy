package app.prepsy.common.data.repository

import app.prepsy.common.data.database.dao.SubjectsDao
import app.prepsy.common.data.database.models.SubjectLocal
import app.prepsy.common.data.database.models.SubjectWithYearsLocal
import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.repository.SubjectRepository
import javax.inject.Inject

class UtmeSubjectRepository @Inject constructor(
    private val subjectsDao: SubjectsDao,
): SubjectRepository {

    override suspend fun getAllSubjects(): List<SubjectEntity> =
        subjectsDao.getAll().map(SubjectLocal::toDomain)

    override suspend fun getYearsForSubject(): List<SubjectWithYearsEntity> =
        subjectsDao.getSubjectsAndYears().map(SubjectWithYearsLocal::toDomain)
}