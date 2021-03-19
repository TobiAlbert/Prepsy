package app.prepsy.data.repository.subject

import app.prepsy.data.models.SubjectData
import app.prepsy.data.models.SubjectWithYearsData

interface SubjectLocalDataSource {
    suspend fun getAllSubjects(): List<SubjectData>
    suspend fun getSubjectsWithYears(): List<SubjectWithYearsData>
}