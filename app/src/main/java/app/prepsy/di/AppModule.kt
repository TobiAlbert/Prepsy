package app.prepsy.di

import app.prepsy.ui.mappers.AnswerMapper
import app.prepsy.domain.entities.Question as QuestionEntity
import app.prepsy.domain.entities.Option as OptionEntity
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.mappers.QuestionMapper
import app.prepsy.ui.models.Option
import app.prepsy.ui.models.Question
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
}