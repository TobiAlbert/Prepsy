package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tobidaada.local.models.QuestionOptionsUserAnswer
import com.tobidaada.local.models.UserScoreLocal

@Dao
interface QuestionDao {

    @Transaction
    @Query("select * from questions where subject_id = :subjectId and year_id = :yearId")
    suspend fun getQuestionsBySubjectAndYear(
        subjectId: String,
        yearId: String
    ): List<QuestionOptionsUserAnswer>

    @Query("select count(ua.option_id) as score, count(questions.id) as total from questions left join user_answers ua on ua.option_id  = questions.right_option where year_id = :yearId and subject_id = :subjectId")
    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreLocal

    @Query("select case count(questions.id) when count(ua.option_id) then 1 else 0 end as is_complete from questions left join user_answers ua on ua.question_id  = questions.id where year_id = :yearId and subject_id = :subjectId")
    suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Int

}