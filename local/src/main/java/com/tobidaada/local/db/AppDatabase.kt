package com.tobidaada.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tobidaada.local.converters.DateConverters
import com.tobidaada.local.dao.AnswerDao
import com.tobidaada.local.dao.QuestionDao
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.models.*

@Database(entities = [
    YearLocal::class,
    SubjectLocal::class,
    QuestionLocal::class,
    OptionLocal::class,
    AnswerLocal::class,
    UserAnswerLocal::class,
    SubjectYearsCrossRef::class
], version = 2, exportSchema = true)
@TypeConverters(DateConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun subjectsDao(): SubjectsDao
    abstract fun questionsDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
}