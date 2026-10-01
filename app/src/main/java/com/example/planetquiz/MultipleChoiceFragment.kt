package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class MultipleChoiceFragment : Fragment() {

    private var questionNumber = 0

    private val questions = arrayOf(
        "What is the largest planet?",
        "Which planet has the most moons?",
        "Which planet spins on its side?"
    )

    private val answers = arrayOf(
        "JUPITER",
        "SATURN",
        "URANUS"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_multiple_choice,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionNumber =
            arguments?.getInt("questionNumber") ?: 0

        val questionText =
            view.findViewById<TextView>(R.id.questionText)

        questionText.text = questions[questionNumber]

        val buttons = listOf(
            R.id.mercuryButton,
            R.id.venusButton,
            R.id.earthButton,
            R.id.marsButton,
            R.id.jupiterButton,
            R.id.saturnButton,
            R.id.uranusButton,
            R.id.neptuneButton
        )

        buttons.forEach { id ->

            view.findViewById<Button>(id).setOnClickListener {

                val selectedAnswer =
                    (it as Button).text.toString()

                val correct =
                    selectedAnswer == answers[questionNumber]

                openAnswerFragment(correct)
            }
        }
    }

    private fun openAnswerFragment(correct: Boolean) {

        val fragment = AnswersFragment()

        val bundle = Bundle()

        bundle.putBoolean("correct", correct)
        bundle.putInt("questionNumber", questionNumber)

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }
}
