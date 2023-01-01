package app.prepsy.common.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.prepsy.common.data.database.converters.DateConverters
import app.prepsy.common.data.database.dao.QuestionDao
import app.prepsy.common.data.database.dao.SubjectsDao
import app.prepsy.common.data.database.dao.UserAnswerDao
import app.prepsy.common.data.database.models.*

@Database(
    entities = [
        YearLocal::class,
        SubjectLocal::class,
        QuestionLocal::class,
        OptionLocal::class,
        UserAnswerLocal::class,
        SubjectYearsCrossRef::class
    ], version = 1, exportSchema = true
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectsDao(): SubjectsDao
    abstract fun questionsDao(): QuestionDao
    abstract fun userAnswerDao(): UserAnswerDao
}