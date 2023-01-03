package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjects @Inject constructor(private val repo: SubjectRepository) {

    suspend operator fun invoke(): List<SubjectEntity> = repo.getAllSubjects()
}