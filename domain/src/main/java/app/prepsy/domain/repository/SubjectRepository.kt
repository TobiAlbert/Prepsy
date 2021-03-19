package app.prepsy.domain.repository

import app.prepsy.domain.entities.SubjectEntity
import app.prepsy.domain.entities.SubjectWithYearsEntity

interface SubjectRepository {
    suspend fun getAllSubjects(): List<SubjectEntity>
    suspend fun getYearsForSubject(): List<SubjectWithYearsEntity>
}