package app.prepsy.di

import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.entities.UserScoreEntity
import app.prepsy.domain.entities.YearEntity
import app.prepsy.ui.mappers.*
import app.prepsy.ui.models.*
import app.prepsy.vendors.ads.AdMobManager
import app.prepsy.vendors.ads.IAdManager
import app.prepsy.domain.entities.SubjectEntity as SubjectEntity
import app.prepsy.domain.entities.QuestionEntity as QuestionEntity
import app.prepsy.domain.entities.OptionEntity as OptionEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun questionMapper(
        questionMapper: QuestionMapper
    ): Mapper<Question, QuestionEntity>

    @Binds
    abstract fun answerMapper(
        answerMapper: AnswerMapper
    ): Mapper<Option, OptionEntity>

    @Binds
    abstract fun subjectMapper(
        subjectMapper: SubjectMapper
    ): Mapper<Subject, SubjectEntity>

    @Binds
    abstract fun yearMapper(
        yearMapper: YearMapper
    ): Mapper<Year, YearEntity>

    @Binds
    abstract fun subjectWithYearMapper(
        subjectWithYearsMapper: SubjectWithYearsMapper
    ): Mapper<SubjectWithYears, SubjectWithYearsEntity>

    @Binds
    abstract fun userScoreMapper(
        mapper: UserScoreMapper
    ): Mapper<UserScore, UserScoreEntity>

    @Binds
    abstract fun adsManager(
        adManager: AdMobManager
    ): IAdManager
}