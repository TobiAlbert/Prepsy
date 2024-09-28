package app.prepsy.common.data.database.dao

import androidx.room.*
import app.prepsy.common.data.database.models.*
import java.util.*

@Dao
interface SubjectsDao {

    @Query("select * from subjects as subjects inner join(select subject_id from subject_years group by subject_id) as d1 on d1.subject_id = subjects.id order by subjects.name asc")
    suspend fun getAll(): List<SubjectLocal>

    @Query("select year_id as id, year_id as name, created_at, updated_at from subject_years where subject_id = :subjectId")
    suspend fun getYearsForSubject(subjectId: String): List<YearLocal>

    /*
        All the functions below are used for initializing test data
     */
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectWithYear(data: SubjectWithYearsLocal) {
        addSubject(data.subject)
        data.years.forEach { it: YearLocal ->
            val date = Date()

            addYear(it)
            addSubjectYear(
                SubjectYearsCrossRef(
                    subjectId = data.subject.id,
                    yearId = it.id,
                    createdAt = date,
                    updatedAt = date
                )
            )
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubject(subject: SubjectLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addYear(year: YearLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllOptions(options: List<OptionLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubjectYear(data: SubjectYearsCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestion(question: QuestionLocal)

    @Transaction
    suspend fun addQuestionAndOption(
        question: QuestionLocal,
        options: List<OptionLocal>
    ) {
        addAllOptions(options)
        addQuestion(question)
    }
}