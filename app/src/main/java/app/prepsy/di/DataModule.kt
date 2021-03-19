package app.prepsy.di

import app.prepsy.data.mapper.*
import app.prepsy.data.models.*
import app.prepsy.data.repository.answer.AnswerRepositoryImpl
import app.prepsy.data.repository.question.QuestionRepositoryImpl
import app.prepsy.data.repository.subject.SubjectRepositoryImpl
import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.entities.YearEntity
import app.prepsy.domain.entities.SubjectEntity as SubjectEntity
import app.prepsy.domain.repository.AnswerRepository
import app.prepsy.domain.repository.QuestionRepository
import app.prepsy.domain.repository.SubjectRepository
import app.prepsy.domain.entities.Question as QuestionEntity
import app.prepsy.domain.entities.Option as OptionEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAnswerDataMapper(
        answerDataMapper: AnswerDataMapper
    ): Mapper<OptionEntity, OptionData>

    @Binds
    abstract fun bindQuestionDataMapper(
        questionDataMapper: QuestionDataMapper
    ): Mapper<QuestionEntity, QuestionData>

    @Binds
    abstract fun bindSubjectDataMapper(
        subjectDataMapper: SubjectDataMapper
    ): Mapper<SubjectEntity, SubjectData>

    @Binds
    abstract fun bindYearDataMapper(
        yearDataMapper: YearsDataMapper
    ): Mapper<YearEntity, YearData>

    @Binds
    abstract fun bindSubjectWithYears(
        subjectsWithYearsDataMapper: SubjectsWithYearsDataMapper
    ): Mapper<SubjectWithYearsEntity, SubjectWithYearsData>

    @Binds
    abstract fun bindQuestionRepository(
        questionRepositoryImpl: QuestionRepositoryImpl
    ): QuestionRepository

    @Binds
    abstract fun bindAnswerRepository(
        answerRepositoryImpl: AnswerRepositoryImpl
    ): AnswerRepository

    @Binds
    abstract fun bindSubjectRepository(
        subjectRepositoryImpl: SubjectRepositoryImpl
    ): SubjectRepository
}