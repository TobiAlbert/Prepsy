package app.prepsy.domain.repository

import app.prepsy.domain.entities.Subject

interface SubjectRepository {
    fun getAllSubjects(): List<Subject>
    fun getYearsForSubject(subjectId: String): List<Int>
}