package app.prepsy.di

import android.content.Context
import androidx.room.Room
import app.prepsy.data.models.*
import app.prepsy.data.repository.answer.AnswerLocalDataSource
import app.prepsy.data.repository.question.QuestionLocalDataSource
import app.prepsy.data.repository.subject.SubjectLocalDataSource
import com.tobidaada.local.dao.QuestionDao
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.dao.UserAnswerDao
import com.tobidaada.local.db.AppDatabase
import com.tobidaada.local.mapper.*
import com.tobidaada.local.models.*
import com.tobidaada.local.source.answers.UserAnswerLocalDataSourceImpl
import com.tobidaada.local.source.question.QuestionLocalDataSourceImpl
import com.tobidaada.local.source.subject.SubjectsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModuleBinds {
    @Binds
    abstract fun bindLocalModuleSubjectMapper(
        mapper: SubjectLocalDataMapper
    ): Mapper<SubjectData, SubjectLocal>

    @Binds
    abstract fun bindLocalOptionMapper(
        mapper: OptionsLocalDataMapper
    ): Mapper<OptionData, OptionLocal>

    @Binds
    abstract fun bindUserScoreMapper(
        mapper: UserScoreLocalDataMapper
    ): Mapper<UserScoreData, UserScoreLocal>

    @Binds
    abstract fun bindLocalSubjectDataSourceImpl(
        dataSource: SubjectsLocalDataSourceImpl
    ): SubjectLocalDataSource

    @Binds
    abstract fun bindLocalQuestionDataSourceImpl(
        dataSource: QuestionLocalDataSourceImpl
    ): QuestionLocalDataSource

    @Binds
    abstract fun bindUserAnswerLocalDataSourceImpl(
        userAnswerLocalDataSourceImpl: UserAnswerLocalDataSourceImpl
    ): AnswerLocalDataSource

    @Binds
    abstract fun bindLocalYearsMapper(
        yearsLocalDataMapper: YearsLocalDataMapper
    ): Mapper<YearData, YearLocal>

    @Binds
    abstract fun bindLocalSubjectWithYearsMapper(
        subjectWithYearsLocalDataMapper: SubjectWithYearsLocalDataMapper
    ): Mapper<SubjectWithYearsData, SubjectWithYearsLocal>

    @Binds
    abstract fun bindLocalUserAnswerMapper(
        userAnswerLocalDataMapper: UserAnswerLocalDataMapper
    ): Mapper<UserAnswerData, UserAnswerLocal>
}

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