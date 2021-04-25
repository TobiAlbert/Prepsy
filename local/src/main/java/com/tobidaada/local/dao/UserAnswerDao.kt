package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tobidaada.local.models.UserAnswerLocal

@Dao
interface UserAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(data: UserAnswerLocal)

    @Query("select * from user_answers where question_id = :questionId")
    suspend fun getByQuestionId(questionId: String): UserAnswerLocal?

    @Query("delete from user_answers where question_id in (select ua.question_id from questions q inner join user_answers ua on ua.question_id = q.id where year_id = :yearId and subject_id = :subjectId)")
    suspend fun clearUserAnswersBySubjectAndYearId(subjectId: String, yearId: String)
}