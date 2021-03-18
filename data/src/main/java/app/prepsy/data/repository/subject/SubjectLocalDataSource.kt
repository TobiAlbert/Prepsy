package app.prepsy.data.repository.subject

import app.prepsy.data.models.SubjectData

interface SubjectLocalDataSource {
    suspend fun getAllSubjects(): List<SubjectData>
}