package app.prepsy.common.data.repository

import app.prepsy.common.data.database.dao.SubjectsDao
import app.prepsy.common.data.database.models.SubjectLocal
import app.prepsy.common.data.database.models.YearLocal
import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.YearEntity
import app.prepsy.common.domain.repository.SubjectRepository
import javax.inject.Inject

class UtmeSubjectRepository @Inject constructor(
    private val subjectsDao: SubjectsDao,
): SubjectRepository {

    override suspend fun getAllSubjects(): List<SubjectEntity> =
        subjectsDao.getAll().map(SubjectLocal::toDomain)

    override suspend fun getYearsForSubject(subjectId: String): List<YearEntity> =
        subjectsDao.getYearsForSubject(subjectId = subjectId).map(YearLocal::toDomain)
}