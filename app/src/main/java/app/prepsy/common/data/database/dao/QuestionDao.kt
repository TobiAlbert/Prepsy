package app.prepsy.common.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import app.prepsy.common.data.database.models.QuestionOptionsUserAnswer
import app.prepsy.common.data.database.models.UserScoreLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Transaction
    @Query("select * from questions where subject_id = :subjectId and year_id = :yearId")
    suspend fun getQuestions(
        subjectId: String,
        yearId: String
    ): List<QuestionOptionsUserAnswer>

    @Transaction
    @Query("select * from questions where subject_id = :subjectId and year_id = :yearId")
    fun getObservableQuestions(
        subjectId: String,
        yearId: String
    ): Flow<List<QuestionOptionsUserAnswer>>

    @Query("select count(ua.option_id) as score, count(questions.id) as total from questions left join user_answers ua on ua.option_id  = questions.right_option where year_id = :yearId and subject_id = :subjectId")
    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreLocal

    @Query("select case count(questions.id) when count(ua.option_id) then 1 else 0 end as is_complete from questions left join user_answers ua on ua.question_id  = questions.id where year_id = :yearId and subject_id = :subjectId")
    suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Int

    @Query("""
        select count(*) from questions q
            inner join user_answers ua on ua.question_id = q.id
            inner join subjects s on s.id = q.subject_id 
            inner join years y on y.id = q.year_id
            where subject_id = :subjectId and year_id = :yearId
    """)
    suspend fun getUserAnswersCount(subjectId: String, yearId: String): Int
}