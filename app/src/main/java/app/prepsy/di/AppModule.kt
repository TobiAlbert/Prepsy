package app.prepsy.di

import app.prepsy.common.domain.entities.*
import app.prepsy.ui.mappers.*
import app.prepsy.ui.models.*
import app.prepsy.utils.AppCoroutineDispatcher
import app.prepsy.utils.AppDispatcher
import app.prepsy.vendors.ads.AdMobManager
import app.prepsy.vendors.ads.IAdManager
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

    @Binds
    abstract fun appDispatcher(
        coroutineDispatcher: AppCoroutineDispatcher
    ): AppDispatcher
}