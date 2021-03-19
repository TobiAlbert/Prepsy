package app.prepsy.domain.usecases

import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectWithYears @Inject constructor(private val repo: SubjectRepository) {
    suspend operator fun invoke(): List<SubjectWithYearsEntity> = repo.getYearsForSubject()
}