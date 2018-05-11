package com.obsidianstudios.alunoifmg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.obsidianstudios.alunoifmg.models.Assignment
import com.obsidianstudios.alunoifmg.models.Note
import com.obsidianstudios.alunoifmg.widgets.UnscrollableLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var dayOfTheWeek: String = ""
    private lateinit var today: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Weather Icons Reference
        // http://erikflowers.github.io/weather-icons/

        val sdf = SimpleDateFormat("EEEE")
        today = Date()
        dayOfTheWeek = sdf.format(today)

        Log.d("TAG", dayOfTheWeek)

        recycler.adapter = NoteListAdapter(notes(), this)
        recycler.layoutManager = UnscrollableLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerAssingments.adapter = AssignmentListAdapter(assignments(), this)
        recyclerAssingments.layoutManager = UnscrollableLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun notes(): List<Note> {
        when (dayOfTheWeek) {
            "segunda-feira" -> return listOf(
                    Note("13:10", "Métodos e Técnicas de Pesquisa"),
                    Note("15:10", "Algoritmos e Programação Procedimental"),
                    Note("17:10", "Geometria Analítica e Álgebra Linear"))
            "terça-feira" -> return listOf(
                    Note("13:10", "Algoritmos e Programação Procedimental"),
                    Note("15:10", "Introdução à Sistemas de Informação"),
                    Note("17:10", "Pré-Cálculo"))
            "quarta-feira" -> return listOf(
                    Note("17:10", "Pré-Cálculo"))
            "quinta-feira" -> return listOf(
                    Note("13:10", "Geometria Analítica e Álgebra Linear"),
                    Note("15:10", "Português Instrumental I"),
                    Note("17:10", "Introdução à Sistemas de Informação"))
            else -> return listOf(
                    Note("-", "Aproveite seu tempo livre hoje para estudar"))
        }
    }

    private fun assignments(): List<Assignment> {
        val sdf = SimpleDateFormat("dd/MM/yyyy") // here set the pattern as you date in string was containing like date/month/year
        val d = sdf.parse("20/12/2011")
        return listOf(
                Assignment("14/05/18", "PROG", "Lista 6",
                        resources.getColor(R.color.blue_500), daysRemaining(Date(2018,4,14))),
                Assignment("21/05/18", "PROG", "Lista 7",
                        resources.getColor(R.color.blue_500), daysRemaining(Date(2018,4,21))),
                Assignment("24/05/18", "GAAL", "Lista 3",
                        resources.getColor(R.color.green_500), daysRemaining(Date(2018,4,24)))
        )
    }

    private fun daysRemaining(date: Date): Int {
//        val diff = date.time - today.time
//        return diff / (24 * 60 * 60 * 1000)
        return (date.date - today.date)
    }
}
