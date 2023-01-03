package app.prepsy.common.domain.usecases

import app.prepsy.common.domain.entities.YearEntity
import app.prepsy.common.domain.repository.SubjectRepository
import javax.inject.Inject

class GetYearsForSubject @Inject constructor(private val repo: SubjectRepository) {
    suspend operator fun invoke(
        subjectId: String,
    ): List<YearEntity> = repo.getYearsForSubject(subjectId = subjectId)
}