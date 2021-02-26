package app.prepsy.domain.usecases

import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectYears @Inject constructor(private val repo: SubjectRepository) {
    operator fun invoke(subjectId: String): List<Int> = repo.getYearsForSubject(subjectId)
}