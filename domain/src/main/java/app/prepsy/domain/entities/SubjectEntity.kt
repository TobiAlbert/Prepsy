package app.prepsy.domain.entities

data class SubjectEntity(
    val id: String = "",
    val name: String
)

data class SubjectWithYearsEntity(
    val subject: SubjectEntity,
    val years: List<YearEntity>
)