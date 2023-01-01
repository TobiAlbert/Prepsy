package app.prepsy.common.domain.repository

import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.SubjectWithYearsEntity

interface SubjectRepository {
    suspend fun getAllSubjects(): List<SubjectEntity>
    suspend fun getYearsForSubject(): List<SubjectWithYearsEntity>
}