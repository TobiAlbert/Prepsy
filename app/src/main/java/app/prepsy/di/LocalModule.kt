package app.prepsy.di

import android.content.Context
import androidx.room.Room
import app.prepsy.common.data.database.dao.QuestionDao
import app.prepsy.common.data.database.dao.SubjectsDao
import app.prepsy.common.data.database.dao.UserAnswerDao
import app.prepsy.common.data.database.db.AppDatabase
import app.prepsy.common.data.database.migrations.callback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        )
            .createFromAsset("database/asset.db")
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun provideSubjectsDao(db: AppDatabase): SubjectsDao = db.subjectsDao()

    @Provides
    @Singleton
    fun provideQuestionsDao(db: AppDatabase): QuestionDao = db.questionsDao()

    @Provides
    @Singleton
    fun provideUserAnswerDao(db: AppDatabase): UserAnswerDao = db.userAnswerDao()
}