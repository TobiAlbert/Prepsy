package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectWithYears @Inject constructor(private val repo: SubjectRepository) {
    suspend operator fun invoke(): List<SubjectWithYearsEntity> = repo.getYearsForSubject()
}