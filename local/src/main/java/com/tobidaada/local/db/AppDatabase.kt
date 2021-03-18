package com.tobidaada.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tobidaada.local.converters.DateConverters
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.models.*

@Database(entities = [
    Year::class,
    SubjectLocal::class,
    Question::class,
    Option::class,
    Answer::class,
    SubjectYearsCrossRef::class
], version = 1, exportSchema = true)
@TypeConverters(DateConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun subjectsDao(): SubjectsDao
}