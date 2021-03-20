package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tobidaada.local.models.SubjectLocal
import com.tobidaada.local.models.SubjectWithYearsLocal

@Dao
interface SubjectsDao {
    @Query("select * from subjects")
    suspend fun getAll(): List<SubjectLocal>

    @Transaction
    @Query("select * from subjects where id = :subjectId")
    suspend fun getYearsBySubjectId(subjectId: String): List<SubjectWithYearsLocal>

    @Transaction
    @Query("select * from subjects as subjects inner join(select subject_id from subject_years group by subject_id) as d1 on d1.subject_id = subjects.id order by subjects.name asc")
    suspend fun getSubjectsAndYears(): List<SubjectWithYearsLocal>
}