package app.prepsy.domain.usecases

import app.prepsy.domain.entities.Subject
import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class GetAllSubjects @Inject constructor(private val repo: SubjectRepository) {

    suspend operator fun invoke(): List<Subject> = repo.getAllSubjects()
}