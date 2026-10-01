package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswersFragment : Fragment() {

    private val answers = arrayOf(
        "JUPITER",
        "SATURN",
        "URANUS"
    )

    private val details = arrayOf(
        "Jupiter is the largest planet and is 2.5 times the mass of all the other planets put together.",
        "Saturn has the most moons and has 82 moons.",
        "Uranus spins on its side with its axis at nearly a right angle to the Sun."
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_answers,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultText =
            view.findViewById<TextView>(R.id.resultText)

        val correctAnswerText =
            view.findViewById<TextView>(R.id.correctAnswerText)

        val detailsText =
            view.findViewById<TextView>(R.id.detailsText)

        val nextButton =
            view.findViewById<Button>(R.id.nextButton)

        val correct =
            arguments?.getBoolean("correct") ?: false

        val questionNumber =
            arguments?.getInt("questionNumber") ?: 0

        if (correct) {
            resultText.text = "CORRECT!"
            resultText.setTextColor(
                android.graphics.Color.GREEN
            )
        } else {
            resultText.text = "WRONG!"
            resultText.setTextColor(
                android.graphics.Color.RED
            )
        }

        correctAnswerText.text =
            "Correct answer: ${answers[questionNumber]}"

        detailsText.text =
            details[questionNumber]

        if (questionNumber < 2) {

            nextButton.text = "NEXT QUESTION"

            nextButton.setOnClickListener {

                val fragment =
                    MultipleChoiceFragment()

                val bundle = Bundle()

                bundle.putInt(
                    "questionNumber",
                    questionNumber + 1
                )

                fragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        fragment
                    )
                    .commit()
            }

        } else {

            nextButton.text = "FINISH"

            nextButton.setOnClickListener {

                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        QuestionsFragment()
                    )
                    .commit()
            }
        }
    }
}
