package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    val alphabet: String,
    val text: String
): Parcelable

@Parcelize
data class Question(
    val text: String,
    val options: List<Option>,
    val answer: Option
): Parcelable

val answerOption1 = Option("A", "0.10 ohms")
val answerOption2 = Option("C", "40%")
val answerOption3 = Option("D", "Bulb is high densityand the bore islarge.")

val questions = listOf(
    Question(
        "The difference of potential between the terminals of a cell is 2.2 volts. " +
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
        "A machine has a velocity ratio of 5. It requires a " +
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
        "A short response time is obtained in a liquid-in-glass thermometer when the",
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
