package com.tobidaada.local.migrations

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

val callback = object: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        deleteMathQuestions(db)
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        deleteMathQuestions(db)
    }

    private fun deleteMathQuestions(db: SupportSQLiteDatabase) {
        // delete mathematics from db
        // language=sql
        val deleteOptionsQuery = """
            delete from options where question_id in(
            	select questions.id 
            	from questions 
            	inner join subjects on subjects.id = questions.subject_id 
            	where subject_id = 'd91f8647-0def-4aeb-939c-804f96e3ce4e'
            )
        """.trimIndent()

        // then delete the math questions
        // language=sql
        val deleteQuestionsQuery = """
           delete from questions where subject_id  = 'd91f8647-0def-4aeb-939c-804f96e3ce4e' 
        """.trimIndent()


        // language=sql
        val deleteMathSubjectQuery = """
            delete from subject_years where subject_id = 'd91f8647-0def-4aeb-939c-804f96e3ce4e';
        """.trimIndent()

        // language=sql
        val deleteMathSubjectYearQuery = """
            delete from subjects where id = 'd91f8647-0def-4aeb-939c-804f96e3ce4e';
        """.trimIndent()

        db.execSQL(deleteOptionsQuery)
        db.execSQL(deleteQuestionsQuery)
        db.execSQL(deleteMathSubjectYearQuery)
        db.execSQL(deleteMathSubjectQuery)
    }
}