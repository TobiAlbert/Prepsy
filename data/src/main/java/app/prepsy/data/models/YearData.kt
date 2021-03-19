package app.prepsy.data.models

data class YearData(
    val id: String,
    val name: String
)

data class SubjectWithYearsData(
    val subject: SubjectData,
    val years: List<YearData>
)