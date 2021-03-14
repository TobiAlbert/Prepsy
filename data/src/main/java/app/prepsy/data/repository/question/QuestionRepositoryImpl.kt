package app.prepsy.data.repository.question

import app.prepsy.domain.entities.Option
import app.prepsy.domain.entities.Question
import app.prepsy.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(): QuestionRepository {
    override fun getQuestions(subjectId: String, yearId: String): List<Question> {

        val answerOption1 = Option("A", "0.10 ohms")
        val answerOption2 = Option("C", "40%")
        val answerOption3 = Option("D", "Bulb is high density and the bore is large.")

        return listOf(
            Question(
                text = "The difference of potential between the terminals of a cell is 2.2 volts. " +
                        "When a 4 ohm resistor is connected across the terminals of this cell, the " +
                        "potential difference is 2 volts. What is the internal " +
                        "resistance of the cell?",
                options = listOf(
                    answerOption1,
                    Option(
                        "B",
                        "0.25ohms"
                    ),
                    Option(
                        "C",
                        "0.40ohms"
                    ),
                    Option(
                        "D",
                        "2.50 ohms"
                    ),
                    Option(
                        "E",
                        "4.00 ohms"
                    )
                ),
                answer = answerOption1
            ),
            Question(
                text = "A machine has a velocity ratio of 5. It requires a " +
                        "50kg weight to overcome a 200kg weight. The " +
                        "efficiency is",
                options = listOf(
                    Option("A", "4%"),
                    Option("B", "5%"),
                    answerOption2,
                    Option("D", "50%"),
                    Option("E", "80%")
                ),
                answer = answerOption2
            ),
            Question(
                text = "A short response time is obtained in a liquid-in-glass thermometer when the",
                options = listOf(
                    Option("A", "Bulb is large and thick-walled."),
                    Option("B", "Stem is long and thin."),
                    Option("C", "Bulb is small and thick-walled."),
                    answerOption3,
                    Option("E", "Bulb is thin-walled and the liquid is a good conductor of heat.")
                ),
                answer = answerOption3
            )
        )
    }
}